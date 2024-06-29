/**
 * Copyright (c) 2018 Cisco Systems
 *
 * Author: Steven Barth <stbarth@cisco.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huawei.yang.explorer.grpc;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.function.Consumer;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.huawei.yang.explorer.grpc.XRGRPC.CreateSubsArgs;
import com.huawei.yang.explorer.grpc.XRGRPC.CreateSubsReply;
import com.huawei.yang.explorer.grpc.gNMIGrpc.gNMIStub;

import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;

public class GRPCClient implements AutoCloseable {
    private ManagedChannel channel;
    private gRPCConfigOperGrpc.gRPCConfigOperStub grpcStub;
    private gNMIStub gnmiStub;
    private long requestID = 0;

    public enum SubscriptionEncoding {
        GPB(2), GPBKV(3), JSON(4);
        private final int value;

        SubscriptionEncoding(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }

    public enum GRPCClientSecurity {
        PLAINTEXT, TLS, TLS_UNVERIFIED;
    }

    public class GRPCException extends Exception {
		GRPCException(String message) {
            super(message);
        }
    }

    public GRPCClient(String host, int port, String username, String password, GRPCClientSecurity security) {
        OkHttpChannelBuilder channelBuilder = OkHttpChannelBuilder.forAddress(host, port);

        switch (security) {
        case PLAINTEXT:
            channelBuilder.usePlaintext();
            break;

        case TLS_UNVERIFIED:
            TrustManager[] trustAllCerts = new TrustManager[] { 
                new X509TrustManager() {     
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {

                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {

                    }

                    public X509Certificate[] getAcceptedIssuers() { 
                        return new X509Certificate[0];
                    }
                }
            }; 
        
            SSLContext sc;
			try {
                sc = SSLContext.getInstance("TLSv1");
                sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
                channelBuilder.sslSocketFactory(sc.getSocketFactory());
			} catch (NoSuchAlgorithmException | KeyManagementException e) {
				e.printStackTrace();
			} 

        case TLS:
            channelBuilder.useTransportSecurity();
            break;
        }

        Metadata headers = new Metadata();
        headers.put(Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER), username);
        headers.put(Metadata.Key.of("password", Metadata.ASCII_STRING_MARSHALLER), password);

        channel = channelBuilder.build();
        //grpcStub = MetadataUtils.attachHeaders(gRPCConfigOperGrpc.newStub(channel), headers);
        //gnmiStub = MetadataUtils.attachHeaders(gNMIGrpc.newStub(channel), headers);
    }

    public static String formatGNMIPath(GNMI.Path path) {
        StringBuilder builder = new StringBuilder();
        if (!path.getOrigin().isEmpty()) {
            builder.append(path.getOrigin());
            builder.append(':');
        } else {
            builder.append('/');
        }
        for (GNMI.PathElem elem: path.getElemList()) {
            builder.append(elem.getName());
            for (Map.Entry<String,String> key: elem.getKeyMap().entrySet()) {
                builder.append('[');
                builder.append(key.getKey());
                builder.append("='");
                builder.append(key.getValue());
                builder.append("']");
            }
            builder.append('/');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public void createSubscription(String subscription, SubscriptionEncoding encoding,
            Consumer<byte[]> telemetryConsumer, Consumer<Throwable> terminationConsumer) {
        CreateSubsArgs arg = CreateSubsArgs.newBuilder().setReqId(++requestID)
                .setEncode(encoding.getValue()).setSubidstr(subscription).build();
        StreamObserver<CreateSubsReply> observer = new StreamObserver<CreateSubsReply>() {
            private Throwable terminationException = null;

			@Override
			public void onNext(CreateSubsReply value) {
                if (!value.getData().isEmpty())
                    telemetryConsumer.accept(value.getData().toByteArray());

                if (!value.getErrors().isEmpty())
                    terminationException = new GRPCException(value.getErrors());
			}

			@Override
			public void onError(Throwable t) {
				terminationConsumer.accept(t);
			}

			@Override
			public void onCompleted() {
				terminationConsumer.accept(terminationException);
			}
        };
        grpcStub.createSubs(arg, observer);
    }

    private GNMI.Subscription parseGNMISubscription(String sensorPath) {
        GNMI.Subscription.Builder subscriptionBuilder = GNMI.Subscription.newBuilder();
        GNMI.Path.Builder pathBuilder = GNMI.Path.newBuilder();
        double interval = -1;

        String paths[] = sensorPath.split(":", 2);
        String path = paths[0];

        if (paths.length > 1) {
            pathBuilder.setOrigin(paths[0]);
            path = paths[1];
        }

        if (path.length() > 0 && path.charAt(0) == '/')
            path = path.substring(1);

        String pathInterval[] = path.split("@", 2);
        path = pathInterval[0];

        if (pathInterval.length > 1)
            interval = pathInterval[1].equals("change") ? 0 : Double.parseDouble(pathInterval[1]);

        subscriptionBuilder.setMode(GNMI.SubscriptionMode.SAMPLE);
        
        if (interval < 0)
            subscriptionBuilder.setMode(GNMI.SubscriptionMode.TARGET_DEFINED);
        else if (interval == 0)
            subscriptionBuilder.setMode(GNMI.SubscriptionMode.ON_CHANGE);
        else
            subscriptionBuilder.setSampleInterval(1000000000 * (long)interval);

        for (String elem: path.split("/")) {
            GNMI.PathElem.Builder elemBuilder = GNMI.PathElem.newBuilder();
            String parts[] = elem.split("\\[");
            elemBuilder.setName(parts[0]);
            for (int i = 1; i < parts.length; ++i) {
                String keyValue[] = parts[i].split("(=')|('])");
                elemBuilder.putKey(keyValue[0], keyValue[1]);
            }
            pathBuilder.addElem(elemBuilder.build());
        }

        return subscriptionBuilder.setPath(pathBuilder.build()).build();
    }

    public void subscribeRequest(Iterable<String> subscriptions,
                                 Consumer<GNMI.Notification> telemetryConsumer, Consumer<Throwable> terminationConsumer) {
        GNMI.SubscriptionList.Builder subscriptionList = GNMI.SubscriptionList.newBuilder()
                .setMode(GNMI.SubscriptionList.Mode.STREAM).setEncoding(GNMI.Encoding.PROTO);

        for (String path: subscriptions)
            subscriptionList.addSubscription(parseGNMISubscription(path));
        
        StreamObserver<GNMI.SubscribeResponse> observer = new StreamObserver<GNMI.SubscribeResponse>() {
            private Throwable terminationException = null;

			@Override
			public void onNext(GNMI.SubscribeResponse response) {
                if (response.getResponseCase() == GNMI.SubscribeResponse.ResponseCase.UPDATE)
                    telemetryConsumer.accept(response.getUpdate());
			}

			@Override
			public void onError(Throwable t) {
				terminationConsumer.accept(t);
			}

			@Override
			public void onCompleted() {
				terminationConsumer.accept(terminationException);
			}
        };
        
        StreamObserver<GNMI.SubscribeRequest> requestObserver = gnmiStub.subscribe(observer);
        requestObserver.onNext(GNMI.SubscribeRequest.newBuilder().setSubscribe(subscriptionList.build()).build());
        requestObserver.onCompleted();
    }

	@Override
	public void close() throws Exception {
		channel.shutdownNow();
    }
}

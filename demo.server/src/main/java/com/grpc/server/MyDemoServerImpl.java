package com.grpc.server;

import com.grpc.dto.DemoResponse;
import com.grpc.dto.DemoRquest;
import com.grpc.dto.DemoServerGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class MyDemoServerImpl extends DemoServerGrpc.DemoServerImplBase {

    @Override
    public void hello(DemoRquest request, StreamObserver<DemoResponse> responseObserver) {
        try {
            DemoResponse response = DemoResponse.newBuilder().setCode(1).setMsg(request.getMsg()).build();
            Thread.sleep(20);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {

        }
    }
}
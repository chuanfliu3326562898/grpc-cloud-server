package com.proxy;

import com.annotation.GrpcService;
import com.client.DemoClient;
import com.dto.GrpcRequest;
import com.dto.GrpcResponse;
import com.status.GrpcResponseStatus;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class GrpcServiceProxy<T> implements InvocationHandler {

    private Class<T> grpcService;

    private Object invoker;

    public GrpcServiceProxy(Class<T> grpcService, Object invoker) {
        this.grpcService = grpcService;
        this.invoker = invoker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy begin:");
        String methodName = method.getName();
        String className = grpcService.getName();
        if ("toString".equals(methodName) && args.length == 0) {
            return className + "@" + invoker.hashCode();
        } else if ("hashCode".equals(methodName) && args.length == 0) {
            return invoker.hashCode();
        } else if ("equals".equals(methodName) && args.length == 1) {
            Object another = args[0];
            return proxy == another;
        }
        GrpcService annotation = grpcService.getAnnotation(GrpcService.class);
        String server = annotation.server();
        GrpcRequest request = new GrpcRequest();
        request.setClazz(className);
        request.setMethod(methodName);
        request.setArgs(args);
        GrpcResponse response = DemoClient.connect(server).handle(serializeType, request);
        if (GrpcResponseStatus.ERROR.getCode() == response.getStatus()) {
            Throwable throwable = response.getException();
            GrpcException exception = new GrpcException(throwable.getClass().getName() + ": " + throwable.getMessage());
            StackTraceElement[] exceptionStackTrace = exception.getStackTrace();
            StackTraceElement[] responseStackTrace = response.getStackTrace();
            StackTraceElement[] allStackTrace = Arrays.copyOf(exceptionStackTrace, exceptionStackTrace.length + responseStackTrace.length);
            System.arraycopy(responseStackTrace, 0, allStackTrace, exceptionStackTrace.length, responseStackTrace.length);
            exception.setStackTrace(allStackTrace);
            throw exception;
        }
        System.out.println("proxy end:");
        return response.getResult();
    }

}

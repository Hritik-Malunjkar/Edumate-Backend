package com.exam.config;

//import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
//import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {
    public ManagedChannel getManagedChannel(String ipAddress, int port, int maxInboundMessageSize) {
        return ManagedChannelBuilder
                .forAddress(ipAddress, port)
                .usePlaintext() // Use plaintext communication (no SSL/TLS)
                .maxInboundMessageSize(maxInboundMessageSize)
                .build();
    }

    @Bean
    public ManagedChannel dmsManagedChannel() {
        return getManagedChannel("localhost", 8098, 15 * 1024 * 1024);
    }

    @Bean
    public com.example.document_management_service.DmsServiceGrpc.DmsServiceBlockingStub dmsServiceBlockingStub(ManagedChannel dmsManagedChannel) {
        return com.example.document_management_service.DmsServiceGrpc.newBlockingStub(dmsManagedChannel);
    }
}

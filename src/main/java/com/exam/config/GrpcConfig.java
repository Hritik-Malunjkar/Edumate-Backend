package com.exam.config;

//import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
//import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    /**
     * Creates a ManagedChannel for a gRPC server.
     *
     * @param ipAddress          The IP address of the gRPC server.
     * @param port               The port of the gRPC server.
     * @param maxInboundMessageSize The maximum inbound message size in bytes.
     * @return A configured ManagedChannel instance.
     */
    public ManagedChannel getManagedChannel(String ipAddress, int port, int maxInboundMessageSize) {
        return ManagedChannelBuilder
                .forAddress(ipAddress, port)
                .usePlaintext() // Use plaintext communication (no SSL/TLS)
                .maxInboundMessageSize(maxInboundMessageSize)
                .build();
    }

    /**
     * Defines a ManagedChannel bean for a specific gRPC server.
     *
     * @return The ManagedChannel instance.
     */
    @Bean
    public ManagedChannel dmsManagedChannel() {
        return getManagedChannel("192.168.31.110", 8098, 15 * 1024 * 1024);
    }
}

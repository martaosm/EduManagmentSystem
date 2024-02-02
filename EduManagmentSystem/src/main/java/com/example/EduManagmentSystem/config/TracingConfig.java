package com.example.EduManagmentSystem.config;

import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Value("${opentracing.jaeger.service-name}")
    private String serviceName;

    @Autowired
    private io.jaegertracing.Configuration configuration;

    @Bean
    public io.opentracing.Tracer tracer() {
        return configuration.getTracer();
    }
}
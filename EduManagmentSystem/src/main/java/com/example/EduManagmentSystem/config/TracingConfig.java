package com.example.EduManagmentSystem.config;

import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.jaegertracing.internal.JaegerTracer;

@Configuration
public class TracingConfig {

    @Bean
    public Tracer tracer() {
        // Configuration and initialization of Jaeger Tracer
        Tracer tracer = JaegerTracer.builder().build(); // Create an instance of Jaeger Tracer
        GlobalTracer.registerIfAbsent(tracer);
        return tracer;
    }
}
package com.example.EduManagmentSystem.config;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ConstSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
public class TracingConfig {

    @Value("${opentracing.jaeger.service-name}")
    private String serviceName;

    @Value("${opentracing.jaeger.sampler-type}")
    private String samplerType;

    @Value("${opentracing.jaeger.sampler-param}")
    private Number samplerParam;

    @Bean
    public io.opentracing.Tracer initTracer() {
        return Configuration.fromEnv(serviceName)
                .withSampler(new Configuration.SamplerConfiguration()
                        .withType(samplerType)
                        .withParam(samplerParam))
                .withReporter(new Configuration.ReporterConfiguration()
                        .withLogSpans(true)
                        .withFlushInterval(1000)
                        .withMaxQueueSize(10000))
                .getTracer();
    }
}
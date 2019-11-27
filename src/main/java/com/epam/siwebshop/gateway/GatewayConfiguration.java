package com.epam.siwebshop.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.http.converter.SerializingHttpMessageConverter;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GatewayConfiguration {

    @Bean
    @ServiceActivator(inputChannel = "convertedItemChannel")
    public HttpRequestExecutingMessageHandler itemGateway() {
        HttpRequestExecutingMessageHandler httpMessageHandler =
                new HttpRequestExecutingMessageHandler("http://localhost:8080/");

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new SerializingHttpMessageConverter());

        httpMessageHandler.setMessageConverters(converters);

        httpMessageHandler.setExtractPayload(false);
        httpMessageHandler.setHttpMethod(HttpMethod.POST);
        httpMessageHandler.setExpectedResponseType(String.class);
        httpMessageHandler.setOutputChannelName("itemResponseChannel");

        return httpMessageHandler;
    }

    @Bean
    @ServiceActivator(inputChannel = "itemResponseChannel")
    public LoggingHandler responseLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(LoggingHandler.Level.INFO);
        loggingHandler.setLogExpressionString("payload.getStatusCodeValue() == 200 ? 'Success' : 'Failure'");

        return loggingHandler;
    }
}

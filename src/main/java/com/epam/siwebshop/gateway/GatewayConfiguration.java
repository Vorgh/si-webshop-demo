package com.epam.siwebshop.gateway;

import com.epam.siwebshop.model.Item;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GatewayConfiguration {

    @Bean
    @ServiceActivator(inputChannel = "outboundItemChannel")
    public HttpRequestExecutingMessageHandler itemGateway() {
        HttpRequestExecutingMessageHandler httpMessageHandler =
                new HttpRequestExecutingMessageHandler("https://siwebshop.free.beeceptor.com/item");

        List<HttpMessageConverter> httpMessageConverters = new ArrayList<>();
        httpMessageConverters.add(new MappingJackson2HttpMessageConverter());

        httpMessageHandler.setHttpMethod(HttpMethod.POST);
        httpMessageHandler.setExpectedResponseType(String.class);
        httpMessageHandler.setOutputChannelName("itemResponseChannel");

        return httpMessageHandler;
    }

    @Bean
    @ServiceActivator(inputChannel = "itemResponseChannel")
    public LoggingHandler responseLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(LoggingHandler.Level.INFO);
        loggingHandler.setLogExpressionString("payload");

        return loggingHandler;
    }
}

package com.epam.siwebshop.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;

@Configuration
public class ChannelConfiguration {

    @Bean
    public PublishSubscribeChannel inputOrderChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PublishSubscribeChannel discountedOrderChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PublishSubscribeChannel processedOrderChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PublishSubscribeChannel outboundItemChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PublishSubscribeChannel convertedItemChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public DirectChannel itemResponseChannel() {
        return new DirectChannel();
    }
}

package com.epam.siwebshop.handler;

import com.epam.siwebshop.model.Item;
import com.epam.siwebshop.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class HandlerConfiguration {

    @Bean
    @Router(inputChannel = "inputOrderChannel")
    public ExpressionEvaluatingRouter discountRouter() {
        ExpressionEvaluatingRouter router = new ExpressionEvaluatingRouter("payload.getItems().size() >= 3");
        router.setChannelMapping("true", "discountedOrderChannel");
        router.setChannelMapping("false", "processedOrderChannel");
        return router;
    }

    @Bean
    @Transformer(inputChannel = "discountedOrderChannel", outputChannel = "processedOrderChannel")
    public HeaderEnricher discountHeaderEnricher() {
        Map<String, ? extends HeaderValueMessageProcessor<?>> headersToAdd =
                Collections.singletonMap("discount", new StaticHeaderValueMessageProcessor<>(0.1));

        return new HeaderEnricher(headersToAdd);
    }

    @Splitter(inputChannel = "processedOrderChannel", outputChannel = "outboundItemChannel")
    public List<Item> orderSplitter(Order order) {
        return order.getItems();
    }

    @Bean
    @Transformer(inputChannel = "outboundItemChannel", outputChannel = "convertedItemChannel")
    public ObjectToJsonTransformer jsonTransformer() {
        return new ObjectToJsonTransformer();
    }
}

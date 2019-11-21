package com.epam.siwebshop;

import com.epam.siwebshop.model.Item;
import com.epam.siwebshop.model.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.support.GenericMessage;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableIntegration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            PublishSubscribeChannel inputOrderChannel = (PublishSubscribeChannel) ctx.getBean("inputOrderChannel");
            Order order = new Order(1L, getItems());

            inputOrderChannel.send(new GenericMessage<>(order));
        };
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "Mouse", 10.0));
        items.add(new Item(2L, "Keyboard", 25.0));
        items.add(new Item(3L, "Monitor", 80.0));

        return items;
    }
}

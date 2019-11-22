package com.epam.siwebshop.transformer;

import com.epam.siwebshop.model.Item;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.Message;

public class ItemToJsonTransformer {

    private ObjectToJsonTransformer objectToJsonTransformer;

    public ItemToJsonTransformer() {
        objectToJsonTransformer = new ObjectToJsonTransformer();
    }

    public Message<?> transform(Message<Item> itemMessage) {
        return objectToJsonTransformer.transform(itemMessage);
    }
}

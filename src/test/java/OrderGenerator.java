import com.epam.siwebshop.channel.ChannelConfiguration;
import com.epam.siwebshop.handler.HandlerConfiguration;
import com.epam.siwebshop.model.Item;
import com.epam.siwebshop.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ChannelConfiguration.class, HandlerConfiguration.class })
public class OrderGenerator {

    @Autowired
    private PublishSubscribeChannel orderInputChannel;

    @Autowired
    private PublishSubscribeChannel processedOrderChannel;

    @Autowired
    private PublishSubscribeChannel outboundItemChannel;

    @Test
    public void testOrderFlow() {
        Order order = new Order(1L, getItems());

        orderInputChannel.send(new GenericMessage<>(order));
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "Mouse", 10.0));
        items.add(new Item(2L, "Keyboard", 25.0));

        return items;
    }
}

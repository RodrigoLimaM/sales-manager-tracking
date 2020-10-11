package br.com.salesmanager.tracking.kafka;

import br.com.salesmanager.tracking.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OrderStatusChangeListener {

    private static final String TOPIC = "yje6oae7-ORDER_STATUS_CHANGE";

    @Autowired
    OrderStatusChangeProducer orderStatusChangeProducer;

    @KafkaListener(topics = "yje6oae7-ORDER_STATUS_CHANGE")
    public void consume(OrderChange orderChange) {
        log.info("Message Listened: {}, Topic: {}", orderChange, TOPIC);
        if (orderChange.getOrderStatus() == OrderStatus.APPROVED) {
            orderChange.setOrderStatus(OrderStatus.PREPARING_FOR_SHIPPING);
            orderStatusChangeProducer.sendMessage(orderChange);
        }
    }
}

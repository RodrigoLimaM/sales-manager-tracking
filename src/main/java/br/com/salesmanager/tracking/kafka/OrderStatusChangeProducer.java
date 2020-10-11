package br.com.salesmanager.tracking.kafka;

import br.com.salesmanager.tracking.model.OrderTracking;
import br.com.salesmanager.tracking.model.Step;
import br.com.salesmanager.tracking.service.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderStatusChangeProducer {

    private static final String TOPIC = "yje6oae7-ORDER_STATUS_CHANGE";

    @Autowired
    private KafkaTemplate<String, OrderChange> kafkaTemplate;

    @Autowired
    private TrackingService trackingService;

    public void sendMessage(OrderChange message) {
        OrderTracking orderTracking = trackingService.findByOrderId(message.getOrderId());
        orderTracking.getSteps().add(Step.builder()
                .orderStatus(message.getOrderStatus())
                .build());

        trackingService.save(orderTracking);
        log.info("Updated order tracking: {}", orderTracking);

        log.info("Produced message: {}, Topic: {}", message, TOPIC);
        kafkaTemplate.send(TOPIC, message);
    }
}

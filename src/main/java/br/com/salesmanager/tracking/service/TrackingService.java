package br.com.salesmanager.tracking.service;

import br.com.salesmanager.tracking.config.exception.InvalidStatusChangeException;
import br.com.salesmanager.tracking.kafka.OrderChange;
import br.com.salesmanager.tracking.kafka.OrderStatusChangeProducer;
import br.com.salesmanager.tracking.model.OrderTracking;
import br.com.salesmanager.tracking.model.Step;
import br.com.salesmanager.tracking.model.enums.OrderStatus;
import br.com.salesmanager.tracking.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackingService {

    @Autowired
    TrackingRepository trackingRepository;

    @Autowired
    OrderStatusChangeProducer orderStatusChangeProducer;

    public OrderTracking findByOrderId(String orderId) {
        return trackingRepository.findByOrderId(orderId);
    }

    public OrderTracking findNullableByOrderId(String orderId) {
        return Optional.ofNullable(this.findByOrderId(orderId)).orElse(OrderTracking.builder()
                .orderId(orderId)
                .steps(new ArrayList<>())
                .build());
    }

    public OrderTracking save(OrderTracking orderTracking) {
        return trackingRepository.save(orderTracking);
    }

    public OrderTracking addStatus(String orderId, OrderStatus status) {
        OrderTracking actualOrderTracking = trackingRepository.findByOrderId(orderId);
        if (actualOrderTracking != null && isValidOrder(actualOrderTracking)) {
            return orderStatusChangeProducer.sendMessage(OrderChange.builder()
                    .orderId(orderId)
                    .orderStatus(status)
                    .build());
        } else {
            throw new InvalidStatusChangeException();
        }
    }

    public OrderTracking incrementStep(OrderTracking orderTracking, OrderStatus status) {
        var steps = orderTracking.getSteps();
        steps.add(Step.builder()
                .orderStatus(status)
                .date(LocalDateTime.now())
                .build());

        orderTracking.setSteps(steps);

        return this.save(orderTracking);
    }

    private boolean isValidOrder(OrderTracking orderTracking) {
        return orderTracking.getSteps()
                        .stream().noneMatch(step -> step.getOrderStatus().isFinisher());
    }
}

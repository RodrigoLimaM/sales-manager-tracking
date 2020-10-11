package br.com.salesmanager.tracking.service;

import br.com.salesmanager.tracking.model.OrderTracking;
import br.com.salesmanager.tracking.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class TrackingService {

    @Autowired
    TrackingRepository trackingRepository;

    public OrderTracking findByOrderId(String orderId) {

        System.out.println("**********\n" +orderId +trackingRepository.findByOrderId(orderId) +"\n*********");
        return Optional.ofNullable(trackingRepository.findByOrderId(orderId)).orElse(OrderTracking.builder()
                .orderId(orderId)
                .steps(new ArrayList<>())
                .build());
    }

    public OrderTracking save(OrderTracking orderTracking) {
        return trackingRepository.save(orderTracking);
    }
}

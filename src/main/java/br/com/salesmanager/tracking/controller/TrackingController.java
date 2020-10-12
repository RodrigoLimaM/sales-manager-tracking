package br.com.salesmanager.tracking.controller;

import br.com.salesmanager.tracking.model.OrderTracking;
import br.com.salesmanager.tracking.model.enums.OrderStatus;
import br.com.salesmanager.tracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    TrackingService trackingService;

    @PostMapping("/addStep/{orderId}")
    public ResponseEntity<OrderTracking> addTrackingStep(@PathVariable String orderId, @RequestParam OrderStatus step) {
        return ResponseEntity.ok(trackingService.addStatus(orderId, step));
    }
}
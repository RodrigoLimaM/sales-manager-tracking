package br.com.salesmanager.tracking.repository;

import br.com.salesmanager.tracking.model.OrderTracking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackingRepository extends MongoRepository<OrderTracking, String> {

    OrderTracking findByOrderId(String orderId);
}

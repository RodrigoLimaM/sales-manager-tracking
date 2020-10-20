package br.com.salesmanager.tracking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PROCESSING_PAYMENT("Processing Payment", false),
    APPROVED("Approved", false),
    CANCELLED("Cancelled", true),
    PREPARING_FOR_SHIPPING("Preparing for shipping", false),
    IN_SEPARATION("In separation", false),
    SENT_TO_CARRIER("Sent to carrier", false),
    OUT_FOR_SHIPMENT("Out for shipment",false),
    DELIVERED("Delivered", true),
    MISSING_RECIPIENT("Missing Recipient", true),
    MISPLACED("Misplaced", true),
    ;

    private String description;

    private boolean isFinisher;
}

package br.com.salesmanager.tracking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PROCESSING_PAYMENT("Processando pagamento", false),
    APPROVED("Pedido aprovado", false),
    CANCELLED("Pedido cancelado", true),
    PREPARING_FOR_SHIPPING("Preparando para envio", false),
    IN_SEPARATION("Em separação", false),
    SENT_TO_CARRIER("Enviado para transportadora", false),
    OUT_FOR_SHIPMENT("Saiu para entrega",false),
    DELIVERED("Entregue", true),
    MISSING_RECIPIENT("Destinatário ausente", true),
    MISPLACED("Extraviado", true),
    ;

    private String description;

    private boolean isFinisher;
}

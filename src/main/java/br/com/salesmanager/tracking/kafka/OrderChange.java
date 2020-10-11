package br.com.salesmanager.tracking.kafka;

import br.com.salesmanager.tracking.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderChange {

    private String customerId;

    private String orderId;

    private OrderStatus orderStatus;
}

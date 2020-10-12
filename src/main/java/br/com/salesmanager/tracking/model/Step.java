package br.com.salesmanager.tracking.model;

import br.com.salesmanager.tracking.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Step {

    @Field(name = "order_status")
    private OrderStatus orderStatus;

    @Field(name = "date")
    private LocalDateTime date;

    public String getDescription(){
        return orderStatus.getDescription();
    }

    public boolean isFinisher(){
        return orderStatus.isFinisher();
    }
}

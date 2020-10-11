package br.com.salesmanager.tracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "trackings")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class OrderTracking {

    @MongoId(value = FieldType.OBJECT_ID)
    private final String _id;

    @Field(name = "order_id")
    private String orderId;

    @Field(name = "steps")
    private List<Step> steps;
}

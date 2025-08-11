package org.example.firstsemfptolayerd.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Order {

    private String orderId;
    private String paymentId;
    private Date date;
    private String customerId;
    private String itemType;
    private String payment_Id;
   private String method;
   private String amount;


    public Order(String orderId, String paymentId, Date date, String customerId, String itemType, String method, String amount) {

        this.orderId = orderId;
        this.paymentId = paymentId;
        this.date = date;
        this.customerId = customerId;
        this.itemType = itemType;
        this.method = method;
        this.amount = amount;

    }
}

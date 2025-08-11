package org.example.firstsemfptolayerd.view.tdm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OrderTM {

    private String orderId;
    private String paymentId;
    private Date date;
    private String customerId;
    private String itemType;
    private String payment_Id;
   private String method;
   private String amount;


    public OrderTM(String orderId, String paymentId, Date date, String customerId, String itemType, String method, String amount) {

        this.orderId = orderId;
        this.paymentId = paymentId;
        this.date = date;
        this.customerId = customerId;
        this.itemType = itemType;
        this.method = method;
        this.amount = amount;

    }
}

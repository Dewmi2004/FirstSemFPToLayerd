package org.example.firstsemfptolayerd.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PaymentDTO {
    String payment_Id;
    String method;
    String date;
    String amount;
}

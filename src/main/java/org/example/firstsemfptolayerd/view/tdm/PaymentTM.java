package org.example.firstsemfptolayerd.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PaymentTM {
    String payment_Id;
    String method;
    String date;
    String amount;
}

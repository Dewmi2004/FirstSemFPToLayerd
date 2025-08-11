package org.example.firstsemfptolayerd.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Payment {
    String payment_Id;
    String method;
    String date;
    String amount;
}

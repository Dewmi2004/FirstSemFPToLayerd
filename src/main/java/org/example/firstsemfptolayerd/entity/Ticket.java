package org.example.firstsemfptolayerd.entity;


import lombok.*;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Ticket {
    private String ticketId;
    private String age;
    private String price;
    private Date date;
    private Time time;
    private String customerId;
    private String employeeId;
    private String Quantity;
    private String fullPrice;
}

package org.example.firstsemfptolayerd.model;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class TicketDTO {
    private String ticketId;
    private String age;
    private String price;
    private Date date;
    private Time time;
    private String customerId;
    private String employeeId;
    private String Quantity;
    private String fullPrice;

    public String getFullPrice() {
        try {
            if (getPrice() == null || getPrice().isBlank() ||
                    getQuantity() == null || getQuantity().isBlank()) {
                return "0";
            }

            double price = Double.parseDouble(getPrice());
            int quantity = Integer.parseInt(getQuantity());
            return String.valueOf(price * quantity);
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    public TicketDTO(String ticketId, String age, String price, Date date, Time time, String customerId, String employeeId, String Quantity) {
        this.ticketId = ticketId;
        this.age = age;
        this.price = price;
        this.date = date;
        this.time = time;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.Quantity = Quantity;

    }
}

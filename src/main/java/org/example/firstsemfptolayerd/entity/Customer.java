package org.example.firstsemfptolayerd.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String id;
    private String name;
    private String address;
    private String gender;
    private String dob;
    private String email;
    private String contact;


    public Customer(String customerId, String customerName, String customerEmail) {
        this.id = customerId;
        this.name = customerName;
        this.email = customerEmail;
    }
}

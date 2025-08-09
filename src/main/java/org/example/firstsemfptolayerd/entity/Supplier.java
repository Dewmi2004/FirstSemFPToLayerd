package org.example.firstsemfptolayerd.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String supId;
    private String name;
    private String contact;
    private String companyAddress;
    private String supplyType;
    private String email;
}

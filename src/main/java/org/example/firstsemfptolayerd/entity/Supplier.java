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

    public Supplier(String supId, String name, String email) {
        this.supId = supId;
        this.name = name;
        this.email = email;
    }

    public Supplier(String email) {
        this.email = email;
    }
}

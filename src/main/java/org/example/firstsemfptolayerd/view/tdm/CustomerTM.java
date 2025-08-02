package org.example.firstsemfptolayerd.view.tdm;
import lombok.*;
import org.example.firstsemfptolayerd.entity.Customer;
import org.example.firstsemfptolayerd.model.CustomerDTO;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private String gender;
    private String dob;
    private String email;
    private String contact;


    public CustomerTM(CustomerDTO customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.gender = customer.getGender();
        this.dob = customer.getDob();
        this.email = customer.getEmail();
        this.contact = customer.getContact();

    }

}

package org.example.firstsemfptolayerd.view.tdm;

import lombok.*;
import org.example.firstsemfptolayerd.model.EmployeeDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmployeeTM {
    private String id;
    private String name;
    private String address;
    private String gender;
    private String dob;
    private String email;
    private String contact;


    public EmployeeTM(EmployeeDTO e) {
        this.id = e.getId();
        this.name = e.getName();
        this.address = e.getAddress();
        this.gender = e.getGender();
        this.dob = e.getDob();
        this.email = e.getEmail();
        this.contact = e.getContact();
    }
}
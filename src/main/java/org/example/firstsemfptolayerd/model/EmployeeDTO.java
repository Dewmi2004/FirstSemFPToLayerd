package org.example.firstsemfptolayerd.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class EmployeeDTO {
    private String id;
    private String name;
    private String address;
    private String gender;
    private String dob;
    private String email;
    private String contact;


}

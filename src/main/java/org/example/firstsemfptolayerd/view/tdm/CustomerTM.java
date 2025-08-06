package org.example.firstsemfptolayerd.view.tdm;
import lombok.*;
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

}

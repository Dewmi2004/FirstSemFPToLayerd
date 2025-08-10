package org.example.firstsemfptolayerd.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PHChemicalDTO {
    private String tankId;
    private String chemicalId;
    private String phLevel;
    private String date;
    private String time;


}

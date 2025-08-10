package org.example.firstsemfptolayerd.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PHChemicalTM {
    private String tankId;
    private String chemicalId;
    private String phLevel;
    private String date;
    private String time;
}
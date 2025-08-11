package org.example.firstsemfptolayerd.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Chemical {
    private String chemicalId;
    private String acidOrBase;
    private String concentration;
    private String storeType;
    private String name;
    private String quantity;
}
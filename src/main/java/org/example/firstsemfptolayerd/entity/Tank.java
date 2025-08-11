package org.example.firstsemfptolayerd.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Tank {
    private String tankId;
    private String glassType;
    private String fishOrPlant;
    private String waterType;
}
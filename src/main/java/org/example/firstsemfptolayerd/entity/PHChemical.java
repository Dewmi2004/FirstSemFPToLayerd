package org.example.firstsemfptolayerd.entity;

    import lombok.*;
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    public class PHChemical {
        private String tankId;
        private String chemicalId;
        private String phLevel;
        private String date;
        private String time;
}

package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Embeddable
@NoArgsConstructor
@JsonRootName("clause")
public class Clause {

    @JsonProperty("unitEndCounter")
    private Integer unitEndCounter;

    @JsonProperty("minimumOphPerYear")
    private Integer minimumOphPerYear;

    @JsonProperty("maximumOph")
    private String maximumOph;

    @JsonProperty("expectedStartStopsPerYear")
    private Integer expectedStartStopsPerYear;

    @JsonProperty("extraWorkDiscountMaterial")
    private Double extraWorkDiscountMaterial;

    @JsonProperty("extraWorkDiscountLabor")
    private Double extraWorkDiscountLabor;

    @JsonProperty("reactionTimePersonnel")
    private String reactionTimePersonnel;

    @JsonProperty("reactionTimeInitiate")
    private String reactionTimeInitiate;

    @JsonProperty("totalEfficiency")
    private String totalEfficiency;

    @JsonProperty("warrantyStartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate warrantyStartDate;

    @JsonProperty("warrantyEndDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate warrantyEndDate;

    @JsonProperty("maintenanceSection")
    private String maintenanceSection;

    @JsonProperty("maintenanceSchedule")
    private String maintenanceSchedule;


}

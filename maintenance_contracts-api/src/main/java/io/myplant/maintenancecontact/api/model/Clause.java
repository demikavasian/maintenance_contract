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

    @JsonProperty("id")
    private Integer unitEndCounter;

    @JsonProperty("minimum_oph_per_year")
    private Integer minimumOphPerYear;

    @JsonProperty("maximum_oph")
    private String maximumOph;

    @JsonProperty("expected_start_stops_per_year")
    private Integer expectedStartStopsPerYear;

    @JsonProperty("extra_work_discount_material")
    private Double extraWorkDiscountMaterial;

    @JsonProperty("extra_work_discount_labor")
    private Double extraWorkDiscountLabor;

    @JsonProperty("reaction_time_personnel")
    private String reactionTimePersonnel;

    @JsonProperty("reaction_time_initiate")
    private String reactionTimeInitiate;

    @JsonProperty("total_efficiency")
    private String totalEfficiency;

    @JsonProperty("warranty_start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate warrantyStartDate;

    @JsonProperty("warranty_end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate warrantyEndDate;

    @JsonProperty("maintenance_section")
    private String maintenanceSection;

    @JsonProperty("maintenance_schedule")
    private String maintenanceSchedule;


}

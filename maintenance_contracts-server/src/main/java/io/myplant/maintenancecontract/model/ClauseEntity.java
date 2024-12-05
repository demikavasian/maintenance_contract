package io.myplant.maintenancecontract.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Embeddable
public class ClauseEntity {

    @Column(name = "unit_end_counter", nullable = false)
    private Integer unitEndCounter;

    @Column(name = "minimum_oph_per_year")
    private Integer minimumOphPerYear;

    @Column(name = "maximum_oph")
    private String maximumOph;

    @Column(name = "expected_start_stops_per_year")
    private Integer expectedStartStopsPerYear;

    @Column(name = "extra_work_discount_material")
    private Double extraWorkDiscountMaterial;

    @Column(name = "extra_work_discount_labor")
    private Double extraWorkDiscountLabor;

    @Column(name = "reaction_time_personnel")
    private String reactionTimePersonnel;

    @Column(name = "reaction_time_initiate")
    private String reactionTimeInitiate;

    @Column(name = "total_efficiency")
    private String totalEfficiency;

    @Column(name = "warranty_start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate warrantyStartDate;

    @Column(name = "warranty_end_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate warrantyEndDate;

    @Column(name = "maintenance_section")
    private String maintenanceSection;

    @Column(name = "maintenance_schedule")
    private String maintenanceSchedule;
}

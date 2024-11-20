package io.myplant.maintenancecontract.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "guarantee")
public class GuaranteeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "unit_of_measure", nullable = false)
    private String unitOfMeasure;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity assetEntity;
}

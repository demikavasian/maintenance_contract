package io.myplant.maintenancecontract.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "guarantee")
@EqualsAndHashCode(exclude = {"assetEntity"})
@ToString(exclude = "assetEntity")
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

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity assetEntity;
}

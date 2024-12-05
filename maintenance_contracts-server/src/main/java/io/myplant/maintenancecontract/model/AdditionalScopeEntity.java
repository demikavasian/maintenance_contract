package io.myplant.maintenancecontract.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "additional_scope")
@EqualsAndHashCode(exclude = {"contractEntity", "assetEntity"})
@ToString(exclude = {"contractEntity", "assetEntity"})
public class AdditionalScopeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "service")
    private String service;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity assetEntity;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private ContractEntity contractEntity;

}

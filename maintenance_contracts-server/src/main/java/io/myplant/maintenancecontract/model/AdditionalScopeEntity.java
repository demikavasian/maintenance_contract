package io.myplant.maintenancecontract.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "additional_scope")
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

package io.myplant.maintenancecontract.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "asset")
public class AssetEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private ClauseEntity clauseEntity;

    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GuaranteeEntity> guaranteeEntities = new HashSet<>();

    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ScopeEntity> scopeEntities = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private ContractEntity contractEntity;
}

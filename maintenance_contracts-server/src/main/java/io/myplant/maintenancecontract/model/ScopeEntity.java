package io.myplant.maintenancecontract.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.myplant.maintenancecontact.api.model.enums.ScopeCoverage;
import io.myplant.maintenancecontact.api.model.enums.ScopeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "scope")
public class ScopeEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_type", nullable = false)
    private ScopeType scopeType;

    @Column(name = "scope_coverage", nullable = false)
    private ScopeCoverage scopeCoverage;

    @Column(name = "additional_attribute")
    private String additionalAttribute;

    @Column(name = "additional_info")
    private String additionalInfo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity assetEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private ActivityEntity activityEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private EventEntity eventEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private ComponentEntity componentEntity;

}

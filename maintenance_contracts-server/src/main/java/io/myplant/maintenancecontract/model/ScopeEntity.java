package io.myplant.maintenancecontract.model;

import io.myplant.maintenancecontact.api.model.enums.ScopeCoverage;
import io.myplant.maintenancecontact.api.model.enums.ScopeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@Entity
@Table(name = "scope")
@EqualsAndHashCode(exclude = "assetEntity")
@ToString(exclude = "assetEntity")
public class ScopeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_type", nullable = false)
    private ScopeType scopeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_coverage", nullable = false)
    private ScopeCoverage scopeCoverage;

    @Column(name = "additional_attribute")
    private String additionalAttribute;

    @Column(name = "additional_info")
    private String additionalInfo;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity assetEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "scope_activity",
            joinColumns = @JoinColumn(name = "scope_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private ActivityEntity activityEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "scope_event",
            joinColumns = @JoinColumn(name = "scope_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private EventEntity eventEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "scope_component",
            joinColumns = @JoinColumn(name = "scope_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private ComponentEntity componentEntity;

}

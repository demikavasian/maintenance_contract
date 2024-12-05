package io.myplant.maintenancecontract.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "component")
@DiscriminatorValue("COMPONENT")
@EqualsAndHashCode(exclude = "scopeEntity")
@ToString(exclude = "scopeEntity")
public class ComponentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "component_node_id")
    private Long componentNodeId;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "component_quantity")
    private String componentQuantity;

    @OneToOne(mappedBy = "componentEntity")
    private ScopeEntity scopeEntity;
}

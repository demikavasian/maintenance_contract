package io.myplant.maintenancecontract.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "component")
@DiscriminatorValue("COMPONENT")
public class ComponentEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "component_node_id")
    private Long componentNodeId;

    @Column(name = "component_name")
    private String componentName;

    @OneToOne(mappedBy = "componentEntity")
    private ScopeEntity scopeEntity;

}

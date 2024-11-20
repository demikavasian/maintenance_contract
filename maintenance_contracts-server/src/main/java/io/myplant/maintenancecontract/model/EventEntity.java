package io.myplant.maintenancecontract.model;

import io.myplant.maintenancecontact.api.model.enums.EventCategory;
import io.myplant.maintenancecontact.api.model.enums.EventService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "event")
@DiscriminatorValue("EVENT")
public class EventEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_service")
    private EventService EventService;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_category")
    private EventCategory EventCategory;

    @OneToOne(mappedBy = "eventEntity")
    private ScopeEntity scopeEntity;

}

package io.myplant.maintenancecontract.model;

import io.myplant.maintenancecontact.api.model.enums.EventCategory;
import io.myplant.maintenancecontact.api.model.enums.EventService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "event")
@DiscriminatorValue("EVENT")
@EqualsAndHashCode(exclude = "scopeEntity")
@ToString(exclude = "scopeEntity")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_service")
    private EventService EventService;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_category")
    private EventCategory EventCategory;

    @Column(name = "event_frequency")
    private String eventFrequency;

    @OneToOne(mappedBy = "eventEntity")
    private ScopeEntity scopeEntity;

}

package io.myplant.maintenancecontract.model;

import io.myplant.maintenancecontact.api.model.enums.ActivityCategory;
import io.myplant.maintenancecontact.api.model.enums.ActivityService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "activity")
@DiscriminatorValue("ACTIVITY")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_service")
    private ActivityService activityService;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_category")
    private ActivityCategory activityCategory;

    @OneToOne(mappedBy = "activityEntity")
    private ScopeEntity scopeEntity;

}

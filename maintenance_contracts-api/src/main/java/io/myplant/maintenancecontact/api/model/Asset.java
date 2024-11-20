package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonRootName("asset")
public class Asset {


    @JsonProperty("id")
    private Long id;

    @Embedded
    private Clause clause;

    @JsonProperty("guarantees")
    private List<Guarantee> guarantees = new ArrayList<>();

    @Valid
    @JsonProperty("scopes")
    private List<Scope> scopes = new ArrayList<>();

}

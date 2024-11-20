package io.myplant.maintenancecontact.api.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.myplant.maintenancecontact.api.model.enums.ContractStatus;
import io.myplant.maintenancecontact.api.model.enums.OfferingType;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonRootName("contract")
public class Contract {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private ContractStatus status;

    @JsonProperty("offeringType")
    private OfferingType offeringType;

    @JsonProperty("effectiveContractStartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate effectiveContractStartDate;

    @JsonProperty("exclusiveEndDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate exclusiveEndDate;

    @Valid
    @JsonProperty("assets")
    private List<Asset> assets = new ArrayList<>();

}

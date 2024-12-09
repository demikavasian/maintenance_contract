package io.myplant.maintenancecontracts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.myplant.maintenancecontact.api.model.Contract;
import io.myplant.maintenancecontract.controller.ContractController;
import io.myplant.maintenancecontract.mapper.*;
import io.myplant.maintenancecontract.mapper.impl.AdditionalScopeMapperImpl;
import io.myplant.maintenancecontract.mapper.impl.AssetMapperImpl;
import io.myplant.maintenancecontract.mapper.impl.ContractMapperImpl;
import io.myplant.maintenancecontract.mapper.impl.ScopeMapperImpl;
import io.myplant.maintenancecontract.model.ContractEntity;
import io.myplant.maintenancecontract.repository.ContractRepository;
import io.myplant.maintenancecontract.service.ContractService;
import io.myplant.maintenancecontracts.testdata.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ContractControllerTest extends TestData {

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    @Mock private ContractRepository contractRepository;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());

        ScopeMapperImpl scopeMapperImpl = new ScopeMapperImpl();

        AdditionalScopeMapperImpl additionalScopeMapperImpl = new AdditionalScopeMapperImpl();

        AssetMapperImpl assetMapper =
                new AssetMapperImpl(
                        Mappers.getMapper(ClauseMapper.class),
                        Mappers.getMapper(GuaranteeMapper.class),
                        scopeMapperImpl,
                        additionalScopeMapperImpl
                        );

        ContractMapperImpl contractMapper = new ContractMapperImpl(
                assetMapper,
                additionalScopeMapperImpl
        );

        ContractService contractService =
                new ContractService(
                        contractMapper,
                        contractRepository,
                        Mappers.getMapper(GuaranteeMapper.class),
                        assetMapper,
                        scopeMapperImpl,
                        additionalScopeMapperImpl
                );

        mvc = MockMvcBuilders.standaloneSetup(new ContractController(contractService)).build();
    }

    @Test
    void createContract() throws Exception {
        when(contractRepository.save(any(ContractEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Contract contract = ContractTestData.request();
        mvc.perform(
                        post("/contract/save")
                                .content(MAPPER.writeValueAsString(contract))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        ArgumentCaptor<ContractEntity> contractEntityCaptor = ArgumentCaptor.forClass(ContractEntity.class);
        verify(contractRepository, times(1)).save(contractEntityCaptor.capture());

        ContractEntity savedEntity = contractEntityCaptor.getValue();
        assertEquals("test-name", savedEntity.getName());
    }

}

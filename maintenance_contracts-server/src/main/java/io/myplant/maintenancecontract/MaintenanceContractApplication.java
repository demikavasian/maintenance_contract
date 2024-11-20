package io.myplant.maintenancecontract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


/**
 * Main application class.
 */
@EnableConfigurationProperties
@EnableMethodSecurity
@SpringBootApplication
@Slf4j
public class MaintenanceContractApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaintenanceContractApplication.class, args);
    }

}

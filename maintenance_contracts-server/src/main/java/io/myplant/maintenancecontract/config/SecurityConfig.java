package io.myplant.maintenancecontract.config;

import io.myplant.auth.EnableTokenAuthentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableMethodSecurity
@EnableTokenAuthentication(sessionCreationPolicy = SessionCreationPolicy.STATELESS)
public class SecurityConfig {

}

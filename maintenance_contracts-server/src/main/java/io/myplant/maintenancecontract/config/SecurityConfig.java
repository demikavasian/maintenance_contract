package io.myplant.maintenancecontract.config;

import io.myplant.auth.EnableTokenAuthentication;
import io.myplant.auth.PathsWithoutAuthorizationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableTokenAuthentication(sessionCreationPolicy = SessionCreationPolicy.STATELESS)
public class SecurityConfig {

    @Bean
    public PathsWithoutAuthorizationCustomizer pathsWithoutAuthorizationCustomizer() {
        return new PathsWithoutAuthorizationCustomizer()
                .addPathPrefix("/ws/api-docs")
                .addPathPrefix("/contract/**");
    }
}

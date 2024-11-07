package io.myplant.template.config;

import io.myplant.auth.EnableTokenAuthentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableTokenAuthentication(sessionCreationPolicy = SessionCreationPolicy.STATELESS)
public class SecurityConfig {
}

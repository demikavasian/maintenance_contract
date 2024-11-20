package io.myplant.maintenancecontract.config;

import io.myplant.filter.WsAuthenticationFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerDocConfig {
    @Value("${appInfo.version}")
    private String apiVersion;

    @Value("${springdoc.base-url}")
    private String baseUrl;

    // TODO veify API description
    private static final String API_TITLE = "maintenance_contracts-api";
    private static final String API_DESCRIPTION = "Provides maintenance_contracts endpoints!";

    private final Environment env;
    private final List<String> localDevProfiles = List.of("local", "dev-env");

    @Bean
    public OpenAPI openApi() {
        final var component =
                new Components()
                        .addSecuritySchemes(
                                WsAuthenticationFilter.X_SESHAT_TOKEN,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name(WsAuthenticationFilter.X_SESHAT_TOKEN));

        final var openApi =
                new OpenAPI()
                        .components(component)
                        .addSecurityItem(
                                new SecurityRequirement()
                                        .addList(WsAuthenticationFilter.X_SESHAT_TOKEN))
                        .info(
                                new Info()
                                        .title(API_TITLE)
                                        .description(API_DESCRIPTION)
                                        .version(apiVersion));

        // needs to be set for services where the base path starts with '/ws/...'
        if (Arrays.stream(env.getActiveProfiles()).noneMatch(localDevProfiles::contains)) {
            final var server = new Server();
            server.setUrl(baseUrl);
            openApi.servers(Collections.singletonList(server));
        }

        return openApi;
    }
}

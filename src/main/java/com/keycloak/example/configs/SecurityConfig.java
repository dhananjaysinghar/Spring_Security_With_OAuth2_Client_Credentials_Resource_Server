package com.keycloak.example.configs;

import com.keycloak.example.configs.handlers.RestAccessDeniedHandler;
import com.keycloak.example.configs.handlers.RestAuthenticationEntryPoint;
import com.keycloak.example.utils.CommonUtils;
import com.keycloak.example.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig {

    @Value(Constants.PLACE_HOLDER_ISSUER_URL)
    private String issuerUri;
    @Value(Constants.PLACE_HOLDER_JWK_SET_URI)
    private String jwkUri;

    @Value(Constants.PLACE_HOLDER_JWK_ALGO)
    private String jwtAlgo;

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkUri).jwsAlgorithm(SignatureAlgorithm.valueOf(jwtAlgo)).build();
        OAuth2TokenValidator<Jwt> tokenValidator = new DelegatingOAuth2TokenValidator<>(JwtValidators.createDefaultWithIssuer(issuerUri));
        jwtDecoder.setJwtValidator(tokenValidator);
        return jwtDecoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChainForForOAuthResourceServer(HttpSecurity http, Environment environment) throws Exception {
        final String[] finalIgnoredUriList = CommonUtils.getDefaultIgnoredUri(environment);
        http.authorizeRequests(authorize -> authorize
                        .antMatchers(finalIgnoredUriList).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .csrf().disable()
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(CommonUtils.jwtAuthenticationConverter()))
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                ).exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler());
        return http.build();
    }
}

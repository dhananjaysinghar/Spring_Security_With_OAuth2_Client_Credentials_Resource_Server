package com.keycloak.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keycloak.example.models.ApiError;
import com.keycloak.example.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CommonUtils {
    private static final String TIME_STAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static String[] getDefaultIgnoredUri(Environment environment) {
        String[] ignoredUriList = Constants.SECURITY_IGNORE_URI;
        String[] configuredIgnoreUri = environment.getProperty(Constants.SECURITY_IGNORE_KEY, String[].class);
        if (configuredIgnoreUri != null && configuredIgnoreUri.length > 0) {
            ignoredUriList = Stream.of(configuredIgnoreUri, Constants.SECURITY_IGNORE_URI).flatMap(Stream::of).toArray(String[]::new);
        }
        return ignoredUriList;
    }

    public static JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<?> authorities = CommonUtils.getData(jwt.getClaims().get("scope"));
            Collection<GrantedAuthority> authorityCollection = authorities.stream()
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            log.info("Validated Authorization token successfully");
            return authorityCollection;
        });
        return jwtAuthenticationConverter;
    }

    public static void printOutputStream(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String message, HttpStatus status) throws IOException {
        ErrorResponse response = ErrorResponse.builder()
                .httpMethod(Objects.requireNonNull(httpServletRequest.getMethod()))
                .requestUri(httpServletRequest.getRequestURI())
                .errorTimestamp(DateTimeFormatter.ofPattern(TIME_STAMP_FORMAT).format(LocalDateTime.now(ZoneOffset.UTC)))
                .errors(List.of(ApiError.builder()
                        .errorCode(Constants.GENERIC_CLIENT_ERROR_CODE)
                        .errorMessage(message)
                        .build())).build();
        httpServletResponse.setStatus(status.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OutputStream out = httpServletResponse.getOutputStream();
        MAPPER.writeValue(out, response);
        out.flush();
    }

    public static void handleClientCredentialsValidationError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpStatus status) throws IOException {
        if (Objects.isNull(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION))
                || Constants.EMPTY_STRING.equals(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION).trim())) {
            printOutputStream(httpServletRequest, httpServletResponse, Constants.CLIENT_CREDENTIAL_TOKEN_BLANK_ERROR, status);
        } else {
            printOutputStream(httpServletRequest, httpServletResponse, Constants.CLIENT_CREDENTIAL_TOKEN_INVALID_ERROR, status);
        }
    }

    public static Collection<?> getData(Object value) {
        if (value instanceof String) {
            return Arrays.stream(((String) value).split(Constants.EMPTY_SPACE_STRING)).collect(Collectors.toList());
        } else if (value instanceof Collection) {
            return (Collection<?>) value;
        } else {
            return Collections.emptyList();
        }
    }
}

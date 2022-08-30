package com.keycloak.example.utils;

public class Constants {

    public static final String EMPTY_STRING = "";
    public static final String EMPTY_SPACE_STRING = " ";
    public static final String GENERIC_CLIENT_ERROR_CODE = "1";
    public static final String[] SECURITY_IGNORE_URI = new String[]{"/actuator/health/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v2/api-docs/**"};
    public static final String SECURITY_IGNORE_KEY = "spring.security.ignore-uri";
    public static final String ERROR_MESSAGE = "Error occurred: {}";
    public static final String PLACE_HOLDER_ISSUER_URL = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}";
    public static final String PLACE_HOLDER_JWK_SET_URI = "${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}";

    public static final String PLACE_HOLDER_JWK_ALGO = "${spring.security.oauth2.resourceserver.jwt.jws-algorithm:RS256}";
    public static final String CLIENT_CREDENTIAL_TOKEN_BLANK_ERROR = "Required request header 'Authorization' is not present";
    public static final String CLIENT_CREDENTIAL_TOKEN_INVALID_ERROR = "Invalid Authorization header";

}

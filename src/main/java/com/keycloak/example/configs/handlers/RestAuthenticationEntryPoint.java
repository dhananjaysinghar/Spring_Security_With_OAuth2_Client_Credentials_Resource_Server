package com.keycloak.example.configs.handlers;

import com.keycloak.example.utils.CommonUtils;
import com.keycloak.example.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException {
        log.error(Constants.ERROR_MESSAGE, exception.getMessage());
        CommonUtils.handleClientCredentialsValidationError(httpServletRequest, httpServletResponse, HttpStatus.UNAUTHORIZED);
    }
}

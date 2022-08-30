package com.keycloak.example.configs.handlers;

import com.keycloak.example.utils.CommonUtils;
import com.keycloak.example.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        log.error(Constants.ERROR_MESSAGE, e.getMessage());
        CommonUtils.handleClientCredentialsValidationError(httpServletRequest, httpServletResponse, HttpStatus.FORBIDDEN);
    }
}

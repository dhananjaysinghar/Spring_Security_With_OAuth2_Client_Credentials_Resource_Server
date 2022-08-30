package com.keycloak.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {


    private String httpMethod;

    private String requestUri;

    private String errorTimestamp;

    private List<ApiError> errors;

}

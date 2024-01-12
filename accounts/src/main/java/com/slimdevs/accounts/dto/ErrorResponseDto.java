package com.slimdevs.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name = "Error response", description = "Schema to hold error response information.")
public class ErrorResponseDto {
    
    @Schema(description = "API path invoked by the client.")
    private String apiPath;

    @Schema(description = "Error code representig the error that has ocurred")
    private HttpStatus errorCode;

    @Schema(description = "Error message in the response")
    private String errorMsg;

    @Schema(description = "Time at which the error ocurred.")
    private LocalDateTime errorTime;
}

package com.slimdevs.accounts.dto;

import com.slimdevs.accounts.constants.AccountConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold successful response information.")
public class ResponseDto {
    
    @Schema(description = "Status code in the response", example = "200")
    private String statusCode;

    @Schema(description = "Status message in the response", example = AccountConstants.MESSAGE_200)
    private String statusMsg;
}

package org.snakeinc.webapi.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorDTO {
    private List<String> errors;

    public ErrorDTO(List<String> errors){
        this.errors = errors;
    }
}

package com.br.lfmelo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Integer status;
    private String message;
    private LocalDateTime date;
    private String path;
    private List<String> errors;

    public StandardError(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> {
            this.errors.add(error.getDefaultMessage());
        });
    }
}

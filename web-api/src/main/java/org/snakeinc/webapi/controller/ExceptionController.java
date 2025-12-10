package org.snakeinc.webapi.controller;

import org.snakeinc.webapi.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleException(MethodArgumentNotValidException ex, WebRequest request){
        ErrorDTO errors = new ErrorDTO(ex.getBindingResult().getAllErrors().stream().map(e->e.getDefaultMessage()).toList());
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }}

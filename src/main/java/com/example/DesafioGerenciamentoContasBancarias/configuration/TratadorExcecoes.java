package com.example.DesafioGerenciamentoContasBancarias.configuration;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarBadRequest(MethodArgumentNotValidException exception){

        List<String> errors = exception.getFieldErrors().stream().map(e -> {
            return e.getDefaultMessage();
        }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

}


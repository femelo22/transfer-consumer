package com.br.lfmelo.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.lfmelo.clients.LogClient;

import static com.br.lfmelo.utils.LogUtil.createLog;

import java.time.LocalDateTime;
import java.util.concurrent.CompletionException;

@RestControllerAdvice
public class HandleException {

    @Autowired LogClient logClient;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> objectNotFoundException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        BindingResult bindingResult = ex.getBindingResult();

        StandardError error = new StandardError(bindingResult);
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Method Argument Not Valid Exception");
        error.setDate(LocalDateTime.now());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(NotFoundException ex, HttpServletRequest request) {

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage("Not Found Exception");
        error.setDate(LocalDateTime.now());
        error.setPath(request.getRequestURI());

        logClient.ingestLog(createLog("Not Found Exception", "Entidade não encontrada", ex.getMessage()));
        System.out.println("passou aqui");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }   
}

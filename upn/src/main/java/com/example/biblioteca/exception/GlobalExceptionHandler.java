package com.example.biblioteca.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. ERRORES DE VALIDACION
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacion(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors()
            .forEach(error -> errores.put(
                error.getField(),
                error.getDefaultMessage()
            ));

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("status", 400);
        respuesta.put("mensaje", "Datos invalidos");
        respuesta.put("errores", errores);
        respuesta.put("fecha", LocalDateTime.now());

        return ResponseEntity.badRequest().body(respuesta);
    }

    // 2. ERRORES DEL SERVICIO
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> argumento(
            IllegalArgumentException ex) {

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("status", 400);
        respuesta.put("mensaje", ex.getMessage());

        return ResponseEntity.badRequest().body(respuesta);
    }

    // 3. ERRORES HTTP ESPECIFICOS
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> estado(
            ResponseStatusException ex) {

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("status", ex.getStatusCode().value());
        respuesta.put("mensaje",
            ex.getReason() != null
                ? ex.getReason()
                : "Error en la solicitud"
        );

        return ResponseEntity
            .status(ex.getStatusCode())
            .body(respuesta);
    }
}
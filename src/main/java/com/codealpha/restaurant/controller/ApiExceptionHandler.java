package com.codealpha.restaurant.controller;

import com.codealpha.restaurant.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> notFound(Exception e){return error(HttpStatus.NOT_FOUND,e.getMessage());}
    @ExceptionHandler({ConflictException.class, IllegalStateException.class})
    public ResponseEntity<Map<String,Object>> conflict(Exception e){return error(HttpStatus.CONFLICT,e.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> invalid(MethodArgumentNotValidException e){
        String message=e.getBindingResult().getFieldErrors().stream().findFirst()
            .map(f->f.getField()+": "+f.getDefaultMessage()).orElse("Invalid request");
        return error(HttpStatus.BAD_REQUEST,message);
    }
    private ResponseEntity<Map<String,Object>> error(HttpStatus status,String message){
        Map<String,Object> body=new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now()); body.put("status",status.value());
        body.put("error",status.getReasonPhrase()); body.put("message",message);
        return ResponseEntity.status(status).body(body);
    }
}

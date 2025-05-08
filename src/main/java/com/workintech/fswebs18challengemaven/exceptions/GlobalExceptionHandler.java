package com.workintech.fswebs18challengemaven.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j // Logging için
@ControllerAdvice // Bu sınıfın tüm controller'lar için global hata yönetimi yapacağını belirtir
public class GlobalExceptionHandler {

    // CardNotFoundException hatalarını yakalar ve uygun HTTP yanıtı döner
    @ExceptionHandler(CardException.class)
    public ResponseEntity<CardErrorResponse> handleCardNotFoundException(CardException ex) {
        log.error("Card Not Found Exception: {}", ex.getMessage(), ex); // Hata logu basar
        CardErrorResponse cardErrorResponse = new CardErrorResponse(ex.getMessage(), ex.getHttpStatus().value());
        return new ResponseEntity<>(cardErrorResponse, ex.getHttpStatus());
    }

    // CardValidationException hatalarını yakalar
    @ExceptionHandler(CardValidationException.class)
    public ResponseEntity<CardErrorResponse> handleCardValidationException(CardValidationException ex) {
        log.error("Card Validation Exception: {}", ex.getMessage(), ex);
        CardErrorResponse cardErrorResponse = new CardErrorResponse(ex.getMessage(), ex.getHttpStatus().value());
        return new ResponseEntity<>(cardErrorResponse, ex.getHttpStatus());
    }

    // Beklenmeyen diğer tüm hataları yakalar
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CardErrorResponse> handleGenericException(Exception ex) {
        log.error("Beklenmeyen Hata: {}", ex.getMessage(), ex);
        String errorMessage = ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : "Unexpected error occurred";
        CardErrorResponse cardErrorResponse = new CardErrorResponse(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(cardErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
package com.workintech.fswebs18challengemaven.exceptions;

import org.springframework.http.HttpStatus;

// Kart verileri geçersiz olduğunda fırlatılacak özel hata sınıfı
public class CardValidationException extends RuntimeException {
    private HttpStatus httpStatus;

    public CardValidationException(String message, HttpStatus httpStatus) {
        super(message); // Hata mesajını üst sınıfa (RuntimeException) ilet
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
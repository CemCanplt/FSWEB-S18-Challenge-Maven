package com.workintech.fswebs18challengemaven.exceptions;

import org.springframework.http.HttpStatus;

// Kart bulunamadığında fırlatılacak özel hata sınıfı
public class CardException extends RuntimeException {
    private HttpStatus httpStatus;

    public CardException(String message, HttpStatus httpStatus) {
        super(message); // Hata mesajını üst sınıfa (RuntimeException) ilet
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
package com.workintech.fswebs18challengemaven.exceptions;

// API kullanıcılarına döneceğimiz hata detaylarını temsil eden sınıf
public class CardErrorResponse {
    private String message;
    private int status;
    // İsteğe bağlı olarak timestamp, path gibi ek alanlar eklenebilir

    public CardErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public CardErrorResponse(String message) {
        this.message = message;
    }

    // Getter metotları (Lombok @Data kullanılabilir)
    public String getMessage() { return message; }
    public int getStatus() { return status; }
}
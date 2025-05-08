package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.exceptions.CardValidationException;

import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
// Önceki adımda @RequestMapping("/workintech") kaldırılmıştı.
// Bu, metodların yollarının artık doğrudan tanımlanması gerektiği anlamına gelir.
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // GET /cards => Tüm kartları getir (Tahmini test yolu)
    @GetMapping("/cards")
    public List<Card> findAll() {
        log.info("GET /cards isteği alındı");
        return cardRepository.findAll();
    }

    // GET /cards/byColor/{color} => Belirli renkteki kartları getir (Tahmini test yolu)
    @GetMapping("/cards/byColor/{color}")
    public List<Card> findByColor(@PathVariable String color) {
        log.info("GET /cards/byColor/{} isteği alındı", color);
        return cardRepository.findByColor(color);
    }

    // GET /cards/byValue/{value} => Belirli değerdeki kartları getir (Tahmini test yolu)
    @GetMapping("/cards/byValue/{value}")
    public List<Card> findByValue(@PathVariable Integer value) {
        log.info("GET /cards/byValue/{} isteği alındı", value);
        return cardRepository.findByValue(value);
    }

    // GET /cards/byType/{type} => Belirli tipteki kartları getir (Tahmini test yolu)
    @GetMapping("/cards/byType/{type}")
    public List<Card> findByType(@PathVariable String type) {
        log.info("GET /cards/byType/{} isteği alındı", type);
        return cardRepository.findByType(type);
    }


    // POST /cards => Yeni kart ekle (Tahmini test yolu)
    @PostMapping("/cards")
    public Card save(@RequestBody Card card) {
        log.info("POST /cards isteği alındı, Card: {}", card);
        CardValidation.validateCard(card);
        card.setId(null);
        return cardRepository.save(card);
    }

    // PUT /cards/ => Kart güncelle (ID request body içinde olmalı) (Testteki PUT yoluna eşleştirildi)
    @PutMapping("/cards/")
    public Card update(@RequestBody Card card) {
        log.info("PUT /cards/ isteği alındı, Card: {}", card);
        if (card.getId() == null) {
            throw new CardValidationException("Card ID must be provided for update", HttpStatus.BAD_REQUEST);
        }
        CardValidation.validateCard(card);

        Card existingCard = cardRepository.findById(card.getId());
        if (existingCard == null) {
            throw new CardException("Card with ID " + card.getId() + " not found for update", HttpStatus.NOT_FOUND);
        }
        return cardRepository.update(card);
    }

    // DELETE /cards/{id} => Belirtilen ID'deki kartı sil
    // **GÜNCELLEME YAPILAN KISIM:**
    // Bu metodun adresini testin gönderdiği "DELETE /cards/1" adresine uyacak şekilde değiştiriyoruz.
    @DeleteMapping("/cards/{id}") // <-- Burası "/card/{id}" iken "/cards/{id}" olarak değiştirildi
    public Card remove(@PathVariable Long id) {
        log.info("DELETE /cards/{} isteği alındı", id); // Log mesajını da güncelledik

        Card existingCard = cardRepository.findById(id);
        if (existingCard == null) {
            throw new CardException("Card with ID " + id + " not found for deletion", HttpStatus.NOT_FOUND);
        }
        return cardRepository.remove(id);
    }
}
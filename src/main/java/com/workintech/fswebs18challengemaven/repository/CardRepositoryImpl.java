package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Import edildi
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.color = :cardColor", Card.class);
        query.setParameter("cardColor", color);

        List<Card> resultList = query.getResultList();

        // **GÜNCELLEME:** Sonuç boşsa hata fırlat
        if (resultList.isEmpty()) {
            throw new CardException("No cards found with color: " + color, HttpStatus.NOT_FOUND);
        }
        // **GÜNCELLEME BİTTİ**

        return resultList; // Sonuç varsa listeyi dön
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c", Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.value = :cardValue", Card.class);
        query.setParameter("cardValue", value);

        List<Card> resultList = query.getResultList();
        // **GÜNCELLEME:** Sonuç boşsa hata fırlat
        if (resultList.isEmpty()) {
            throw new CardException("No cards found with value: " + value, HttpStatus.NOT_FOUND);
        }
        // **GÜNCELLEME BİTTİ**
        return resultList;
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.type = :cardType", Card.class);
        query.setParameter("cardType", type);

        List<Card> resultList = query.getResultList();
        // **GÜNCELLEME:** Sonuç boşsa hata fırlat
        if (resultList.isEmpty()) {
            throw new CardException("No cards found with type: " + type, HttpStatus.NOT_FOUND);
        }
        // **GÜNCELLEME BİTTİ**
        return resultList;
    }

    @Override
    @Transactional
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Override
    @Transactional
    public Card remove(Long id) {
        // findById metodu artık hata fırlattığı için burada null kontrolüne gerek yok.
        // findById çağrısı hata fırlatırsa bu metot zaten çalışmaya devam etmez.
        Card cardToRemove = findById(id); // findById metodu çağrılıyor

        // findById hata fırlatmadıysa kart bulundu demektir.
        entityManager.remove(cardToRemove); // Kartı sil
        return cardToRemove; // Silinen kartı dön
    }

    @Override
    public Card findById(Long id) {
        Card card = entityManager.find(Card.class, id);

        if (card == null) {
            throw new CardException("Card with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }

        return card;
    }
}
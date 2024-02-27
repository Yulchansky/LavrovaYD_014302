package com.atm.controller;

import com.atm.model.Cards;
import com.atm.model.enums.CurrencyType;
import com.atm.repo.CardsRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CardsContTest {

    @Mock
    private CardsRepo cardsRepo;

    @InjectMocks
    private CardsCont cardsCont;

    @Test
    void cardAdd() {
        MockitoAnnotations.initMocks(this);
        String name = "Test Name";
        String number = "1234567890123456";
        String cvv = "123";
        String date = "01/25";
        String fio = "Test FIO";
        String pin = "1234";
        float money = 100.0f;
        CurrencyType currency = CurrencyType.USD;

        when(cardsRepo.getReferenceById(anyLong())).thenReturn(new Cards());
        when(cardsRepo.save(any(Cards.class))).thenReturn(new Cards());

        String result = cardsCont.cardAdd(name, number, cvv, date, fio, pin, money, currency);

        assertEquals("redirect:/cards", result);

        verify(cardsRepo).save(any(Cards.class));
    }

    @Test
    void cardDelete() {
        MockitoAnnotations.initMocks(this);

        String result = cardsCont.cardDelete(1L);

        verify(cardsRepo).deleteById(1L);
    }

    @Test
    void cardMoneyAdd() {
        MockitoAnnotations.initMocks(this);

        when(cardsRepo.getReferenceById(anyLong())).thenReturn(new Cards());

        cardsCont.cardMoneyAdd(100.0f, 1L);

        verify(cardsRepo).save(any(Cards.class));
    }
}

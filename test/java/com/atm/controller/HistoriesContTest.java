package com.atm.controller;

import com.atm.model.ATMs;
import com.atm.model.Cards;
import com.atm.model.Settings;
import com.atm.model.enums.CurrencyType;
import com.atm.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HistoriesContTest {

    @Mock
    private HistoriesRepo historiesRepo;

    @Mock
    private CardsRepo cardsRepo;

    @Mock
    private StatATMsRepo statATMsRepo;

    @Mock
    private ATMsRepo atmsRepo;

    @Mock
    private SettingsRepo settingsRepo;

    @InjectMocks
    private HistoriesCont historiesCont;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void historyAdd() {
        Model model = mock(Model.class);
        long atmId = 1L;
        long cardId = 1L;
        String pin = "1234";
        String cvv = "567";
        CurrencyType currency = CurrencyType.BYN;
        String moneyBYN = "100";
        String moneyOther = "0";

        ATMs atm = new ATMs();
        Cards card = mock(Cards.class);
        when(card.getId()).thenReturn(cardId);
        when(card.getPin()).thenReturn(String.valueOf(pin.hashCode()));
        when(card.getCvv()).thenReturn(String.valueOf(cvv.hashCode()));
        when(card.getCurrency()).thenReturn(CurrencyType.BYN);
        when(card.getMoney()).thenReturn(1000.0f);

        when(cardsRepo.getReferenceById(cardId)).thenReturn(card);
        when(atmsRepo.getReferenceById(atmId)).thenReturn(atm);


        Settings settings = new Settings();
        when(settingsRepo.findAll()).thenReturn(Collections.singletonList(settings));

        String viewName = historiesCont.historyAdd(model, atmId, cardId, pin, cvv, currency, moneyBYN, moneyOther);

        assertEquals("ATM", viewName);
    }
}
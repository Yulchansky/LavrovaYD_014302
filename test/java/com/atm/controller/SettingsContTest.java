package com.atm.controller;

import com.atm.model.Settings;
import com.atm.model.enums.CurrencyType;
import com.atm.repo.SettingsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SettingsContTest {

    @Mock
    private SettingsRepo settingsRepo;

    @InjectMocks
    private SettingsCont settingsCont;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void settingLimit() {
        CurrencyType currency = CurrencyType.BYN;
        int BYN = 100;
        int USD = 200;
        int EUR = 400;
        int RUB = 300;

        List<Settings> settingsList = new ArrayList<>();
        Settings settings = new Settings();
        settingsList.add(settings);

        when(settingsRepo.findAll()).thenReturn(settingsList);

        String viewName = settingsCont.settingLimit(currency, BYN, USD, EUR, RUB);

        Settings setting = settingsRepo.findAll().get(0);

        assertEquals(BYN, setting.getLimitBYN());
        assertEquals(USD, setting.getLimitUSD());
        assertEquals(EUR, setting.getLimitEUR());
        assertEquals(RUB, setting.getLimitRUB());

        verify(settingsRepo, times(1)).save(setting);

        assertEquals("redirect:/settings", viewName);
    }
}
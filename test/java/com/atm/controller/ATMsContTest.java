package com.atm.controller;

import com.atm.model.ATMs;
import com.atm.repo.ATMsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ATMsContTest {

    @Mock
    private ATMsRepo atmsRepo;

    @InjectMocks
    private ATMsCont atmsCont;

    @Test
    void productAddNew() {
        MockitoAnnotations.initMocks(this);
        MockMultipartFile mockFile = new MockMultipartFile("file", new byte[0]);
        atmsCont.productAddNew(null, "ATM name", 0.05f, "ATM address", "ATM serial", mockFile);

        verify(atmsRepo).save(any(ATMs.class));
    }

    @Test
    void productEditOld()  {
        MockitoAnnotations.initMocks(this);

        long id = 1L;
        String name = "New ATM Name";
        float com = 0.05f;
        String address = "New ATM Address";
        String serial = "New ATM Serial";
        ATMs atm = new ATMs("Old ATM Name", 0.1f, "Old ATM Address", "Old ATM Serial");
        when(atmsRepo.getReferenceById(id)).thenReturn(atm);
        atmsCont.productEditOld(null, name, com, address, serial, null, id);
        verify(atmsRepo).save(atm);
    }

    @Test
    void productDelete() {
        MockitoAnnotations.initMocks(this);
        long id = 1L;
        atmsCont.productDelete(id);
        verify(atmsRepo).deleteById(id);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Инициализация моков
    }
    @Test
    void ATMs() {
        ATMs testATM = new ATMs();
        testATM.setStatus(true);

        when(atmsRepo.getReferenceById(eq(1L))).thenReturn(testATM);
        when(atmsRepo.save(any(ATMs.class))).thenReturn(testATM);

        atmsCont.ATMs(1L);

        verify(atmsRepo, times(1)).save(any(ATMs.class));

        assertEquals(false, testATM.isStatus());
    }

}
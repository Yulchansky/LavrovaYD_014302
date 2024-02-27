package com.atm.controller;

import com.atm.model.Users;
import com.atm.model.enums.Role;
import com.atm.repo.UsersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersContTest {

    private UsersCont usersCont;

    private UsersRepo usersRepo;

    @BeforeEach
    void setUp() {
        usersRepo = mock(UsersRepo.class);
        usersCont = new UsersCont();
        usersCont.setUsersRepo(usersRepo);
    }

    @Test
    void userDelete() {
        // Arrange
        Long userId = 1L;

        // Act
        String viewName = usersCont.UserDelete(userId);

        // Assert
        assertEquals("redirect:/users", viewName);
        verify(usersRepo, times(1)).deleteById(userId);
    }

    @Test
    void userUpdate() {
        Long userId = 1L;
        Role newRole = Role.MANAGER;
        Users user = new Users();

        // Stubbing
        when(usersRepo.getReferenceById(userId)).thenReturn(user);

        // Act
        String viewName = usersCont.UserUpdate(userId, newRole);

        // Assert
        assertEquals("redirect:/users", viewName);
        assertEquals(newRole, user.getRole());
        verify(usersRepo, times(1)).save(user);
    }
}

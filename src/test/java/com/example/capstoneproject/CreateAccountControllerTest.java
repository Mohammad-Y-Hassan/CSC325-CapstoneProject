package com.example.capstoneproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountControllerTest {

    CreateAccountController controller;

    @BeforeEach
    void setUp() {
        controller = new CreateAccountController();
    }

    @Test
    void isValidEmail() {
        // Valid email addresses
        assertTrue(controller.isValidEmail("example@gmail.com"));
        assertTrue(controller.isValidEmail("ew2e@gmail.com"));
        assertTrue(controller.isValidEmail("namesurname@yahoo.com"));
        assertTrue(controller.isValidEmail("johndoe@gmail.com"));
        assertTrue(controller.isValidEmail("jane123@farmingdale.edu"));
        assertTrue(controller.isValidEmail("username@yahoo.com"));
        assertTrue(controller.isValidEmail("admin@gmail.com"));
        assertTrue(controller.isValidEmail("info@yahoo.com"));
        assertTrue(controller.isValidEmail("support@farmingdale.edu"));
        assertTrue(controller.isValidEmail("contact123@gmail.com"));
        assertTrue(controller.isValidEmail("user456@yahoo.com"));
        assertTrue(controller.isValidEmail("hello@farmingdale.edu"));
        assertTrue(controller.isValidEmail("test_user@yahoo.com"));

        // Invalid email addresses
        assertFalse(controller.isValidEmail("invalid_email.com"));
        assertFalse(controller.isValidEmail("user@.com"));
        assertFalse(controller.isValidEmail("name@domain@another.com"));
        assertFalse(controller.isValidEmail("example@gmail"));
        assertFalse(controller.isValidEmail("invalid@com"));
        assertFalse(controller.isValidEmail("user123@"));
        assertFalse(controller.isValidEmail("name@domain@other.com"));
        assertFalse(controller.isValidEmail("hello@domain."));
        assertFalse(controller.isValidEmail("missing_at_sign.com"));
        assertFalse(controller.isValidEmail("invalid.email@domain"));
        assertFalse(controller.isValidEmail("user@com@web.com"));
        assertFalse(controller.isValidEmail("no_domain_part@."));
        assertFalse(controller.isValidEmail("invalid123@domain..com"));
    }
}
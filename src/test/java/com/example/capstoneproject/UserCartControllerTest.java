package com.example.capstoneproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UserCartControllerTest {

    UserCartController controller;

    @BeforeEach
    void setUp() {
        controller = new UserCartController();
    }

    @Test
    void validExpDate() {
        assertTrue(controller.validExpDate("06/24"));
        assertTrue(controller.validExpDate("12/25"));
        assertTrue(controller.validExpDate("06/24"));
        assertTrue(controller.validExpDate("12/25"));
        assertTrue(controller.validExpDate("06/24"));
        assertTrue(controller.validExpDate("12/25"));
        assertTrue(controller.validExpDate("06/24"));
        assertTrue(controller.validExpDate("12/25"));
        assertTrue(controller.validExpDate("06/24"));
        assertTrue(controller.validExpDate("12/25"));

        assertFalse(controller.validExpDate("13/24"));
        assertFalse(controller.validExpDate("05/23"));
        assertFalse(controller.validExpDate("13/04"));
        assertFalse(controller.validExpDate("02/12"));
        assertFalse(controller.validExpDate("03/22"));
        assertFalse(controller.validExpDate("w449"));
        assertFalse(controller.validExpDate("w3ee6"));
        assertFalse(controller.validExpDate("s8e2"));
        assertFalse(controller.validExpDate("p3e0"));
        assertFalse(controller.validExpDate("6501"));
    }

    @Test
    void validCcv() {
        assertTrue(controller.validCcv("123"));
        assertTrue(controller.validCcv("113"));
        assertTrue(controller.validCcv("988"));
        assertTrue(controller.validCcv("029"));
        assertTrue(controller.validCcv("183"));
        assertTrue(controller.validCcv("642"));

        assertFalse(controller.validCcv("121 1"));
        assertFalse(controller.validCcv("123 p4"));
        assertFalse(controller.validCcv("12a"));
        assertFalse(controller.validCcv("12"));
        assertFalse(controller.validCcv("4"));
        assertFalse(controller.validCcv("12a"));
    }
}
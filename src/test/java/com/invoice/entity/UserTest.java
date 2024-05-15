package com.invoice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;
    
    @BeforeEach
    public void setUp() {
        user = new User();
    }
    
    @Test
    @DisplayName("Testing the userId")
    void testUserId() {
        Integer userId = 1;
        user.setUserId(userId);
        assertEquals(userId, user.getUserId());
    }
    
    @Test
    @DisplayName("Testing the userName")
    void testUserName() {
        String userName = "testuser";
        user.setUserName(userName);
        assertEquals(userName, user.getUserName());
    }
    
    @Test
    @DisplayName("Testing the user password")
    void testPassword() {
        String password = "password";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }
    
    @Test
    @DisplayName("Testing the user email")
    void testEmail() {
        String email = "user@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }
    
    @Test
    @DisplayName("Testing the user created date")
    void testCreatedDate() {
        LocalDate createdDate = LocalDate.now();
        user.setCreatedDate(createdDate);
        assertEquals(createdDate, user.getCreatedDate());
    }
    
    @Test
    @DisplayName("Testing the user created invoices")
    void testInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        invoices.add(invoice1);
        invoices.add(invoice2);
        user.setInvoices(invoices);
        assertEquals(invoices, user.getInvoices());
    }
}

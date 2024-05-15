package com.invoice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class InvoiceTest {

    private Invoice invoice;
    
    @BeforeEach
    public void setUp() {
        invoice = new Invoice();
    }
    
    @Test()
    @DisplayName("Testing the invoiceId")
    void testInvoiceId() {
        Integer invoiceId = 1;
        invoice.setInvoiceId(invoiceId);
        assertEquals(invoiceId, invoice.getInvoiceId());
    }
    
    @Test
    @DisplayName("Testing the client name")
    void testClientName() {
        String clientName = "Client ABC";
        invoice.setClientName(clientName);
        assertEquals(clientName, invoice.getClientName());
    }
    
    @Test
    @DisplayName("Testing the invoice amount")
    void testInvoiceAmount() {
        Double invoiceAmount = 3500.00;
        invoice.setInvoiceAmount(invoiceAmount);
        assertEquals(invoiceAmount, invoice.getInvoiceAmount());
    }
    
    @Test
    @DisplayName("Testing the invoice created date")
    void testCreatedDate() {
        LocalDate createdDate = LocalDate.now();
        invoice.setCreatedDate(createdDate);
        assertEquals(createdDate, invoice.getCreatedDate());
    }
    
    @Test
    @DisplayName("Testing the description")
    void testDescription() {
        String description = "Description of the invoice";
        invoice.setDescription(description);
        assertEquals(description, invoice.getDescription());
    }
    
    @Test
    @DisplayName("Testing the invoice user")
    void testUser() {
        User user = new User();
        invoice.setUser(user);
        assertEquals(user, invoice.getUser());
    }
}

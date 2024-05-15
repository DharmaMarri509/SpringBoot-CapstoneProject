package com.invoice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.invoice.entity.Invoice;
import com.invoice.entity.User;

@SpringBootTest
class InvoiceRepositoryTest {

    @MockBean
    private InvoiceRepository invoiceRepository; // Mocking the InvoiceRepository interface

    @Test
    @DisplayName("Test for getting invoices based on userId")
    void testGetInvoices() {
    	
    	Integer userId = 1;
        User user = new User();
        user.setUserId(userId);
        
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
        invoice1.setUser(user);

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice1);
        
        when(invoiceRepository.findByUserId(userId)).thenReturn(invoices);

        List<Invoice> result = invoiceRepository.findByUserId(userId);

        assertEquals(invoices, result);
    }
    
    
    @Test
    @DisplayName("Testing for deleting a invoice of a user")
    void testDeleteInvoice() {
    	Integer userId = 1;
        User user = new User();
        user.setUserId(userId);
        
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
        invoice1.setUser(user);
    	
    	
    	invoiceRepository.deleteById(1);
    	
    	Optional<Invoice> deletedProduct = invoiceRepository.findById(invoice1.getInvoiceId());
        assertFalse(deletedProduct.isPresent());

    }
    
    
    @Test
    @DisplayName("Testing the saving the invoices of a particular user")
    void testSaveInvoice() {
    	Invoice invoice = new Invoice();
    	invoice.setClientName("kohli");
    	invoice.setInvoiceAmount(4000.00);
    	invoice.setDescription("buy laptops");
    	
    	when(invoiceRepository.save(invoice)).thenReturn(invoice);
    	Invoice inv = invoiceRepository.save(invoice);
    	
    	assertEquals(invoice, inv);

    }
    
//    @Test
//    @DisplayName("Testing finding invoice based on the invoice id")
//    void testFindInvoiceByID() {
//    	Invoice invoice = new Invoice();
//    	invoice.setInvoiceId(1);
//    	invoice.setClientName("kohli");
//    	invoice.setInvoiceAmount(4000.00);
//    	invoice.setDescription("buy laptops");
//    	
//    	when(invoiceRepository.findById(1).get()).thenReturn(invoice);
//    	Invoice inv = invoiceRepository.findById(1).get();
//    	
//    	assertEquals(invoice, inv);
//
//    }
}


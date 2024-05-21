package com.invoice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.invoice.entity.Invoice;
import com.invoice.entity.User;
import com.invoice.exception.InvoiceAmountException;
import com.invoice.exception.UserAlreadyExistException;
import com.invoice.repository.InvoiceRepository;
import com.invoice.repository.UserRepository;

@SpringBootTest
class InvoiceServiceImplTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private Invoice invoice;

    @BeforeEach
    public void setup() {
        Integer userId = 2;
        User user = userRepository.findById(userId).get();
        invoice = new Invoice();
        invoice.setClientName("client");
        invoice.setInvoiceAmount(3000.0);
        invoice.setCreatedDate(LocalDate.now());
        invoice.setDescription("description");
        invoice.setUser(user);
       
    }
    
    @Test
    @DisplayName("testcase to save the invoice")
    @Disabled
    void testSaveInvoice() {
    	Invoice savedInvoice = invoiceRepository.save(invoice);
    	assertThat(savedInvoice).isNotNull();
    	
    }

    @Test
    @DisplayName("testcase to get the invoices based on the userId")
    void testGetInvoices() {
    	Integer userId = 2;
        List<Invoice> invoices = invoiceService.getInvoices(userId);
        assertThat(invoices.size()).isPositive() ;
    }


    @Test
    void testAddInvoiceWithInvalidAmount() {
        Invoice newInvoice = new Invoice();
        newInvoice.setClientName("newTestClient");
        newInvoice.setInvoiceAmount(2000.0);
        newInvoice.setDescription("newTestDescription");
        newInvoice.setUser(user);
        assertThrows(InvoiceAmountException.class, () -> invoiceService.addInvoice(2, newInvoice));
    }

    @Test
    void testAddInvoiceWithNonExistingUser() {
        Invoice newInvoice = new Invoice();
        newInvoice.setClientName("newTestClient");
        newInvoice.setInvoiceAmount(4000.0);
        newInvoice.setDescription("newTestDescription");
        newInvoice.setUser(new User());
        assertThrows(UserAlreadyExistException.class, () -> invoiceService.addInvoice(5, newInvoice));
    }
    
    @Test
    void testGetInvoiceById() {
    	assertThat(invoiceRepository.findById(19).get()).isNotNull();
    }
    
    @Test
    @Disabled
    void testDeleteInvoiceById() {
    	String deleteMsg = invoiceService.deleteInvoice(2);
    	assertThat(deleteMsg).isNotNull();
    }
    
    

}
package com.invoice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.invoice.entity.Invoice;
import com.invoice.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InvoiceRepositoryTestEx {
	
	private Logger log = LoggerFactory.getLogger(UserRepositoryTestEx.class);

	@MockBean
	private InvoiceRepository invoiceRepository;
	
	
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
        
        

        List<Invoice> result = invoiceRepository.findByUserId(userId);

        assertThat(result).isNotNull();
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
	
	@Test
    @DisplayName("Testing finding invoice based on the invoice id")
    void testFindInvoiceByID() {
    	Invoice invoice = new Invoice();
    	invoice.setInvoiceId(1);
    	invoice.setClientName("kohli");
    	invoice.setInvoiceAmount(4000.00);
    	invoice.setDescription("buy laptops");
    	when(invoiceRepository.findById(1).get()).thenReturn(invoice);
    	Invoice findInvoice = invoiceRepository.findById(1).get();
    	log.warn(findInvoice.getClientName());
    	assertEquals("kohli",findInvoice.getClientName());

    }
	 @Test
	 @DisplayName("testcase for updating the data in the invoice")
	 void testUpdateInvoice() {
		 Invoice invoice = new Invoice();
		 invoice.setInvoiceId(1);
    	 invoice.setClientName("kohli");
    	 invoice.setInvoiceAmount(4000.00);
    	 invoice.setDescription("buy laptops");
    	 
    	 Double invoiceAmount = 3500.00;
    	 invoice.setInvoiceAmount(invoiceAmount);
    	 Invoice savedInvoice = invoiceRepository.save(invoice);
    	 assertThat(savedInvoice.getInvoiceAmount()).isEqualTo(3500.00);
	 }
	 
	 @Test
	 @DisplayName("testcase to save the invoice without the user")
	 void testSaveInvoiceWithOutUser() {
		 
		 
		
    	 
    	
	 }
	
}
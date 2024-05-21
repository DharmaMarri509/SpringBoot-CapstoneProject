package com.invoice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.invoice.entity.Invoice;
import com.invoice.entity.User;
import com.invoice.exception.InvoiceAmountException;
import com.invoice.exception.RecordNotFoundException;
import com.invoice.exception.UserAlreadyExistException;
import com.invoice.repository.InvoiceRepository;
import com.invoice.repository.UserRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private InvoiceRepository irepo;
	
	private UserRepository repo;
	
	private Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	
	public InvoiceServiceImpl(InvoiceRepository irepo,UserRepository repo){
		this.irepo = irepo;
		this.repo = repo;
	}
	
	
	public List<Invoice> getInvoices(Integer userId){
		return irepo.findByUserId(userId);
	}
	
	public Invoice addInvoice(Integer userId, Invoice invoice) throws InvoiceAmountException, UserAlreadyExistException {
		User findUser = null;
	
			Double amount = invoice.getInvoiceAmount();
			if(amount<3000) {
				throw new InvoiceAmountException(amount+" is not sufficient, minimum amount should be 3000");
			}
			findUser = repo.findById(userId).orElseThrow(() -> new UserAlreadyExistException("user is already found with id :- "+userId));
			invoice.setUser(findUser);
		
		
		return irepo.save(invoice);
	}
	
	public Invoice getInvoiceById(Integer invoiceId) throws RecordNotFoundException {
		return irepo.findById(invoiceId).orElseThrow(() -> new RecordNotFoundException("record not found with id :-"+invoiceId));
		
	}
	
	
	public Invoice updateInvoice(Integer invoiceId, Invoice invoice) {
		Invoice existingInvoice = null;
		try {
			existingInvoice = irepo.findById(invoiceId).orElseThrow(() -> new RecordNotFoundException("record not found with id :- "+invoiceId));
			existingInvoice.setInvoiceId(invoiceId);
			existingInvoice.setClientName(invoice.getClientName());
			existingInvoice.setInvoiceAmount(invoice.getInvoiceAmount()); 
			existingInvoice.setDescription(invoice.getDescription()); 
			irepo.save(existingInvoice);
			
			
		} catch (RecordNotFoundException e) {
			log.info(e.getMessage());
		}
		return existingInvoice;
	}
	
	public String deleteInvoice(Integer invoiceId) {
		irepo.deleteById(invoiceId);
		return "invoice deleted";
	}
}

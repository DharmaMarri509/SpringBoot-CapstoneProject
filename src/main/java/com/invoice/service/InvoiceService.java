package com.invoice.service;

import java.util.List;

import com.invoice.entity.Invoice;
import com.invoice.exception.InvoiceAmountException;
import com.invoice.exception.RecordNotFoundException;
import com.invoice.exception.UserAlreadyExistException;

public interface InvoiceService {

	public List<Invoice> getInvoices(Integer userId);
	
	public Invoice addInvoice(Integer userId, Invoice invoice) throws InvoiceAmountException, UserAlreadyExistException ;
	
	public Invoice getInvoiceById(Integer invoiceId) throws RecordNotFoundException;
	
	public String deleteInvoice(Integer invoiceId);
	
	public Invoice updateInvoice(Integer invoiceId,Invoice invoice);
}

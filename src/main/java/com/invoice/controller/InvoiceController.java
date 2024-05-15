package com.invoice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entity.Invoice;
import com.invoice.exception.InvoiceAmountException;
import com.invoice.exception.RecordNotFoundException;
import com.invoice.exception.UserAlreadyExistException;
import com.invoice.service.InvoiceServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	private InvoiceServiceImpl iservice;
	
	InvoiceController(InvoiceServiceImpl iservice){
		this.iservice = iservice;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Invoice> addInvoice(@Valid @RequestParam("userId")Integer userId, @RequestBody Invoice invoice)
			throws InvoiceAmountException, UserAlreadyExistException{
		
		return new ResponseEntity<>(iservice.addInvoice(userId, invoice), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getInvoicesList",
			produces = "application/json")
	public ResponseEntity<List<Invoice>> invoices(@RequestParam("userId")Integer userId){
		
		return new ResponseEntity<>(iservice.getInvoices(userId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getInvoice",
			produces = "application/json")
	public ResponseEntity<Invoice> getInvoiceById(@RequestParam("invoiceId")Integer invoiceId) throws RecordNotFoundException{
		return new ResponseEntity<>(iservice.getInvoiceById(invoiceId), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Invoice> updateInvoiceById(@RequestParam("invoiceId")Integer invoiceId, @RequestBody Invoice invoice){
		return new ResponseEntity<>(iservice.updateInvoice(invoiceId, invoice), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteInvoice(@RequestParam("invoiceId")Integer invoiceId){
		return new ResponseEntity<>(iservice.deleteInvoice(invoiceId), HttpStatus.OK);
	}
	
}

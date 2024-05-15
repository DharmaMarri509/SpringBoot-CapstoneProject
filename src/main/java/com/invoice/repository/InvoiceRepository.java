package com.invoice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query(value = "select * from invoice where user_id=?",
			nativeQuery = true)
	public List<Invoice> findByUserId(Integer userId);
}

package com.invoice.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer invoiceId;
	
	@NotBlank(message = "Clientname is required")
	@Size(min=5, message = "clientname should contain atleast 5 characters")
	@Column(nullable = false)
	private String clientName;
	
	
	@Min(value=3000, message = "invoice amount should be minimum 3000")
	@Positive(message = "Amount must be positive")
	@Column(nullable = false)
	private Double invoiceAmount;
	
	//@CreationTimestamp
    @PastOrPresent(message = "Event date must be in the past or present")
    @Column(nullable= false)
	private LocalDate createdDate;
	
	private String description;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;

	
}

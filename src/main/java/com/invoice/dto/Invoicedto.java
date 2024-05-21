package com.invoice.dto;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoicedto {
	
	private int invoiceId;
    private Date invoiceDate;

   @Min(value = 3000, message = "Amount should be greater than 3000")
   private double amount;

   @NotBlank(message = "Please provide client name")
   private String clientName;

   private String description;

   @NotNull(message = "User ID is required")
   private int userId;

	

}

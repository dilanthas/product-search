package com.telenor.productsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private String type;

	private String properties;

	private BigDecimal price;

	@JsonProperty("store_address")
	private String storeAddress;


}

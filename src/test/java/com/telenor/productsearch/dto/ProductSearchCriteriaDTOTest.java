package com.telenor.productsearch.dto;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductSearchCriteriaDTOTest {

	@Test
	public void shouldReturnEqualTrue() {

		// Given
		ProductSearchCriteriaDTO criteriaDTO1 = ProductSearchCriteriaDTO.builder().city("Stockholm").color("green")
				.minGBLimit(10).maxGBLimit(100).minGBLimit(10).minPrice(
						BigDecimal.valueOf(10)).maxPrice(BigDecimal.valueOf(100)).type("phone").property("color").build();
		ProductSearchCriteriaDTO criteriaDTO2 = ProductSearchCriteriaDTO.builder().city("Stockholm").color("green")
				.minGBLimit(10).maxGBLimit(100).minGBLimit(10).minPrice(
						BigDecimal.valueOf(10)).maxPrice(BigDecimal.valueOf(100)).type("phone").property("color").build();

		// When

		// Then
		Assert.assertEquals(criteriaDTO1, criteriaDTO2);
	}

	@Test
	public void shouldReturnEqualFalse(){
		// Given
		ProductSearchCriteriaDTO criteriaDTO1 = ProductSearchCriteriaDTO.builder().city("Bromma").color("red")
				.minGBLimit(10).maxGBLimit(100).minGBLimit(10).minPrice(
						BigDecimal.valueOf(10)).maxPrice(BigDecimal.valueOf(100)).type("phone").property("color").build();
		ProductSearchCriteriaDTO criteriaDTO2 = ProductSearchCriteriaDTO.builder().city("Stockholm").color("green")
				.minGBLimit(10).maxGBLimit(100).minGBLimit(10).minPrice(
						BigDecimal.valueOf(10)).maxPrice(BigDecimal.valueOf(100)).type("phone").property("color").build();

		// When

		// Then
		Assert.assertFalse(criteriaDTO1.equals(criteriaDTO2));
	}
}

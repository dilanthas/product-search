package com.telenor.productsearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.dto.ProductResponse;
import com.telenor.productsearch.dto.ProductSearchCriteriaDTO;
import com.telenor.productsearch.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ProductService service;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldReturn404WhenNoResultsAvailable() throws Exception{
		// Given
		String type = "subscription";

		ProductResponse<List<ProductDTO>> response = new ProductResponse<>();

		List<ProductDTO> resultList = new ArrayList<>();

		response.setData(resultList);



		Mockito.when(service.getProducts(any(ProductSearchCriteriaDTO.class))).thenReturn(response);

		// When
		mockMvc.perform(
				get("/product?type=" + type )).andExpect(status().is4xxClientError());

	}
	@Test
	public void shouldReturnProductsWithGivenCriteria() throws Exception{

		// Given
		String type = "subscription";
		BigDecimal minPrice = BigDecimal.valueOf(10);
		BigDecimal maxPrice = BigDecimal.valueOf(1000);
		String city = "Stockholm";
		String street = "Karlsson gatan";
		String property = "gb_limit";
		String color = "";
		Integer minGBLimit = 10;
		Integer maxGBLimit = 100;
		ProductSearchCriteriaDTO criteriaDTO = ProductSearchCriteriaDTO.builder().type(type).minPrice(minPrice)
				.maxPrice(maxPrice).city(city).property(property).color(color).minGBLimit(minGBLimit).maxGBLimit(maxGBLimit)
				.build();

		ProductDTO productDTO = ProductDTO.builder().type(type).price(maxPrice).properties(property + ":" + minGBLimit)
				.storeAddress(street + "," + city).build();
		ProductResponse<List<ProductDTO>> response = new ProductResponse<>();

		List<ProductDTO> resultList = new ArrayList<>();
		resultList.add(productDTO);

		response.setData(resultList);


		Mockito.when(service.getProducts(criteriaDTO)).thenReturn(response);

		// When
		mockMvc.perform(
				get("/product?type=" + type + "&max_price=" + maxPrice + "&min_price=" + minPrice + "&property:color="
						+ color + "&city=" + city + "&property:gb_limit_min=" + minGBLimit + "&property:gb_limit_max="
						+ maxGBLimit + "&property=" + property)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[0].type", is(type)))
				.andExpect(jsonPath("$.data.[0].properties", is(property + ":" + minGBLimit)))
				.andExpect(jsonPath("$.data.[0].price").value(is(maxPrice),BigDecimal.class))
				.andExpect(jsonPath("$.data.[0].store_address",is(street + "," + city)));

		// Then

	}

}

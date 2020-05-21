package com.telenor.productsearch.controller;

import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.dto.ProductResponse;
import com.telenor.productsearch.dto.ProductSearchCriteriaDTO;
import com.telenor.productsearch.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

	Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@RequestMapping("/test")
	public @ResponseBody
	String greeting() {
		return "Hello, This is product service";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseEntity<ProductResponse> getProducts(@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "min_price", required = false) BigDecimal minPrice,
			@RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "property", required = false) String property,
			@RequestParam(name = "property:color", required = false) String color,
			@RequestParam(name = "property:gb_limit_min", required = false) Integer minGBLimit,
			@RequestParam(name = "property:gb_limit_max", required = false) Integer maxGBLimit) {

		ProductSearchCriteriaDTO criteriaDTO = ProductSearchCriteriaDTO.builder().type(type).minPrice(minPrice)
				.maxPrice(maxPrice).city(city).property(property).color(color).minGBLimit(minGBLimit).maxGBLimit(maxGBLimit)
				.build();
		LOGGER.debug("ProductSearchCriteriaDTO : {}", criteriaDTO.toString());
		ProductResponse<List<ProductDTO>> response = productService.getProducts(criteriaDTO);

		if (response.getData().isEmpty()) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

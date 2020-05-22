package com.telenor.productsearch.mapper;

import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.entity.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
public class ProductMapperTest {

	@Autowired
	ModelMapper mapper;

	private ProductMapper productMapper;

	@TestConfiguration
	static class ProductMapperTestConfig {
		@Bean
		public ModelMapper walletService() {
			return new ModelMapper();
		}
	}
	@Before
	public void init(){

		productMapper = new ProductMapper(mapper);
	}

	@Test
	public void shouldMapDTOForColorAttribute(){

		// Given
		Product product = Product.builder().cityName("Stockholm").streetName("Alvina gränden").color("red").type("phone").price(
				BigDecimal.valueOf(1000)).property("color").build();

		ProductDTO dto = ProductDTO.builder().type("phone").price(BigDecimal.valueOf(1000)).build();


		// When
		ProductDTO mapperDto = productMapper.mapToDto(product);

		// Then
		Assert.assertEquals(mapperDto.getProperties(),"color:red");
		Assert.assertEquals(mapperDto.getPrice(),BigDecimal.valueOf(1000));
		Assert.assertEquals(mapperDto.getType(),"phone");
		Assert.assertEquals(mapperDto.getStoreAddress(),"Alvina gränden, Stockholm");
	}

	@Test
	public void shouldMapDTOForGBLimitAttribute(){

		// Given
		Product product = Product.builder().cityName("Stockholm").streetName("Alvina gränden").color("red").type("subscription").price(
				BigDecimal.valueOf(1000)).property("gb_limit").gbLimit(100).build();

		// When
		ProductDTO mapperDto = productMapper.mapToDto(product);

		// Then
		Assert.assertEquals(mapperDto.getProperties(),"gb_limit:100");
		Assert.assertEquals(mapperDto.getPrice(),BigDecimal.valueOf(1000));
		Assert.assertEquals(mapperDto.getType(),"subscription");
		Assert.assertEquals(mapperDto.getStoreAddress(),"Alvina gränden, Stockholm");
	}
}

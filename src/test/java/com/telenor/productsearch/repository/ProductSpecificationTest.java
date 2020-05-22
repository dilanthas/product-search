package com.telenor.productsearch.repository;

import com.telenor.productsearch.entity.Product;
import com.telenor.productsearch.repository.specification.ProductSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.main.banner-mode=off")
public class ProductSpecificationTest {

	@Autowired
	private ProductRepository repository;


	@Before
	public void init(){

		repository.deleteAll();

		Product productPhoneGreen = Product.builder().type("phone").price(BigDecimal.valueOf(1000)).streetName("abc").cityName("xyz").property("color").color("green").build();
		Product productPhoneRed = Product.builder().type("phone").price(BigDecimal.valueOf(150)).streetName("abc").cityName("xyz").property("color").color("red").build();

		Product productSubscription50 = Product.builder().type("subscription").price(BigDecimal.valueOf(2000)).streetName("abc").cityName("xyz").property("gb_limit").gbLimit(50).build();
		Product productSubscription25 = Product.builder().type("subscription").price(BigDecimal.valueOf(100)).streetName("abc").cityName("zzz").property("gb_limit").gbLimit(25).build();

		repository.save(productPhoneGreen);
		repository.save(productPhoneRed);


		repository.save(productSubscription25);
		repository.save(productSubscription50);
	}

	@Test
	public void shouldFilterFromCorrectType(){

		// Given
		String productType = "phone";

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withType(productType)));

		// Then
		Assert.assertEquals(productList.size(),2);
	}

	@Test
	public void shouldIgnoreTypeWhenNull(){
		// Given
		String productType = null;

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withType(productType)));

		// Then
		Assert.assertEquals(productList.size(),4);
	}

	@Test
	public void shouldIgnoreTypeWhenEmpty(){
		// Given
		String productType = "";

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withType(productType)));

		// Then
		Assert.assertEquals(productList.size(),4);
	}

	@Test
	public void shouldFilterWithMinPrice(){

		// Given
		BigDecimal minPrice = BigDecimal.valueOf(150);

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withMinPrice(minPrice)));

		// Then
		Assert.assertEquals(productList.size(),3);
	}

	@Test
	public void shouldFilterWithMaxPrice(){
		// Given
		BigDecimal maxPrice = BigDecimal.valueOf(1000);

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withMaxPrice(maxPrice)));

		// Then
		Assert.assertEquals(productList.size(),3);
	}

	@Test
	public void shouldFilterWithCity(){
		// Given
		String city = "zzz";

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withCityName(city)));

		// Then
		Assert.assertEquals(productList.size(),1);
	}

	@Test
	public void shouldFilterWithPropertyType(){

		// Given
		String property = "gb_limit";

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withProperty(property)));

		// Then
		Assert.assertEquals(productList.size(),2);

	}

	@Test
	public void shouldFilterWithColor(){
		// Given
		String color = "green";

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withColor(color)));

		// Then
		Assert.assertEquals(productList.size(),1);
	}

	@Test
	public void shouldFilterWithMinGB(){
		// Given
		Integer minGB = 50;

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withMinGB(minGB)));

		// Then
		Assert.assertEquals(productList.size(),1);
	}

	@Test
	public void shouldFilterWithMaxGB(){
		// Given
		Integer maxGB = 10;

		// When
		List<Product> productList = repository.findAll(Specification.where(ProductSpecification.withMinGB(maxGB)));

		// Then
		Assert.assertEquals(productList.size(),2);
	}
}

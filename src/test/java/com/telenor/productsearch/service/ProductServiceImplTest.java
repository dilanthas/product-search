package com.telenor.productsearch.service;

import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.dto.ProductResponse;
import com.telenor.productsearch.dto.ProductSearchCriteriaDTO;
import com.telenor.productsearch.entity.Product;
import com.telenor.productsearch.mapper.ProductMapper;
import com.telenor.productsearch.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.telenor.productsearch.constants.ErrorConstants.NO_DATA_FOUND;
import static com.telenor.productsearch.constants.ErrorConstants.RESULT_COUNT;

public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl service;

	@Mock
	private ProductRepository repository;

	@Mock
	private ProductMapper mapper;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnProductList(){

		// Given
		ProductSearchCriteriaDTO criteriaDTO = Mockito.mock(ProductSearchCriteriaDTO.class);

		Product product1 = Mockito.mock(Product.class);
		Product product2 = Mockito.mock(Product.class);

		List<Product> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);

		Mockito.when(repository.findAll(ArgumentMatchers.any(Specification.class))).thenReturn(products);

		ProductDTO productDTO1 = Mockito.mock(ProductDTO.class);
		ProductDTO productDTO2 = Mockito.mock(ProductDTO.class);

		List<ProductDTO> productDTOs = new ArrayList<>();
		productDTOs.add(productDTO1);
		productDTOs.add(productDTO2);

		Mockito.when(mapper.mapToDtoList(products)).thenReturn(productDTOs);

		// When
		ProductResponse<List<ProductDTO>> response = service.getProducts(criteriaDTO);

		// Then
		Assertions.assertNotNull(response.getData());
		Assertions.assertEquals(response.getData().size(),2);
		Assertions.assertEquals(response.getMessage(),String.format(RESULT_COUNT, 2));
	}

	@Test
	public void shouldReturnNoDataFound(){

		// Given
		ProductSearchCriteriaDTO criteriaDTO = Mockito.mock(ProductSearchCriteriaDTO.class);
		List<Product> products = new ArrayList<>();

		Mockito.when(repository.findAll(ArgumentMatchers.any(Specification.class))).thenReturn(products);

		// When
		ProductResponse<List<ProductDTO>> response = service.getProducts(criteriaDTO);

		// Then
		Assertions.assertNotNull(response.getData());
		Assertions.assertEquals(response.getData().size(),0);
		Assertions.assertEquals(response.getMessage(),NO_DATA_FOUND);
	}
}

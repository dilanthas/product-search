package com.telenor.productsearch.service;

import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.dto.ProductResponse;
import com.telenor.productsearch.dto.ProductSearchCriteriaDTO;
import com.telenor.productsearch.entity.Product;
import com.telenor.productsearch.mapper.ProductMapper;
import com.telenor.productsearch.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.telenor.productsearch.constants.ErrorConstants.NO_DATA_FOUND;
import static com.telenor.productsearch.constants.ErrorConstants.RESULT_COUNT;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withCityName;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withColor;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withMaxGB;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withMaxPrice;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withMinGB;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withMinPrice;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withProperty;
import static com.telenor.productsearch.repository.specification.ProductSpecification.withType;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductMapper mapper;

	/**
	 * Search products for given criteria
	 * @param searchCriteriaDTO
	 * @return
	 */
	@Override
	public ProductResponse<List<ProductDTO>> getProducts(ProductSearchCriteriaDTO searchCriteriaDTO) {

		List<Product> products = repository.findAll(
				Specification.where(withType(searchCriteriaDTO.getType())).and(withMinPrice(searchCriteriaDTO.getMinPrice()))
						.and(withMaxPrice(searchCriteriaDTO.getMaxPrice())).and(withCityName(searchCriteriaDTO.getCity())).and(withProperty(searchCriteriaDTO.getProperty())).and(withMaxGB(searchCriteriaDTO.getMaxGBLimit())).and(withMinGB(searchCriteriaDTO.getMinGBLimit())).and(withColor(searchCriteriaDTO.getColor())));

		ProductResponse<List<ProductDTO>> response = new ProductResponse<>();
		if(products.isEmpty()){

			logger.debug("No Results found ");

			response.setMessage(NO_DATA_FOUND);
			response.setData(new ArrayList<>());

		}else{
			logger.debug("Number of Results found : {}", products.size());
			response.setData(mapper.mapToDtoList(products));
			response.setMessage(String.format(RESULT_COUNT,products.size()));
		}

		return response;
	}

}

package com.telenor.productsearch.mapper;

import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

	private static String COLOR = "color";
	private static String GB_LIMIT = "gb_limit";
	@Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> mapToDtoList(List<Product> products) {
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    public ProductDTO mapToDto(Product product) {

	    ProductDTO dto = modelMapper.map(product, ProductDTO.class);
	    dto.setStoreAddress(product.getStreetName()+", "+product.getCityName());

	    String property = product.getProperty();
	    if (COLOR.equals(property)) {
		    dto.setProperties(property + ":" + product.getColor());
	    } else if (GB_LIMIT.equals(product.getProperty())) {
		    dto.setProperties(property + ":" + product.getGbLimit());
	    }
        return dto;
    }
}

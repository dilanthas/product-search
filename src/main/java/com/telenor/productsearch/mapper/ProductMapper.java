package com.telenor.productsearch.mapper;

import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.telenor.productsearch.constants.Constants.COLOR;
import static com.telenor.productsearch.constants.Constants.GB_LIMIT;

@Component
public class ProductMapper {



    private ModelMapper modelMapper;

	@Autowired
	public ProductMapper(ModelMapper modelMapper){
		this.modelMapper = modelMapper;
	}
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

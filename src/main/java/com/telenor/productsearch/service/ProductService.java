package com.telenor.productsearch.service;

import com.telenor.productsearch.dto.ProductDTO;
import com.telenor.productsearch.dto.ProductResponse;
import com.telenor.productsearch.dto.ProductSearchCriteriaDTO;
import com.telenor.productsearch.entity.Product;

import java.util.List;

public interface ProductService {

    public ProductResponse<List<ProductDTO>> getProducts(ProductSearchCriteriaDTO searchCriteriaDTO);
}

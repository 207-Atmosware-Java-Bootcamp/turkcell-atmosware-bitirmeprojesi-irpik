package com.kadirirpik.business.service;

import com.kadirirpik.business.dto.ProductDto;
import com.kadirirpik.entities.Product;

import java.util.List;

public interface IProductservice {
    ProductDto saveProduct(ProductDto productDto);
    List<ProductDto> productList();
    void deleteProduct(Long id);
    ProductDto updateProduct(Long id, ProductDto productDto);
    ProductDto getProductDto(Long id);
}

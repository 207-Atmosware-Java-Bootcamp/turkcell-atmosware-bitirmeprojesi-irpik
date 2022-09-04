package com.kadirirpik.business.service.Impl;

import com.kadirirpik.bean.ModelMapperBean;
import com.kadirirpik.business.dto.ProductDto;
import com.kadirirpik.business.service.IProductservice;
import com.kadirirpik.entities.Product;
import com.kadirirpik.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductservice {
    private IProductRepository productRepository;
    private ModelMapperBean modelMapperBean;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository, ModelMapperBean modelMapperBean) {
        this.productRepository = productRepository;
        this.modelMapperBean = modelMapperBean;
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = productRepository.save(modelMapperBean.modelMapperMethod().map(productDto, Product.class));
        return modelMapperBean.modelMapperMethod().map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> productList() {
        List<ProductDto> productDtoList = productRepository.findAll().stream()
                .map(product -> modelMapperBean.modelMapperMethod().map(product, ProductDto.class))
                .collect(Collectors.toList());
        return productDtoList;
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            product.get().setProductName(productDto.getProductName());
            product.get().setProductTrade(productDto.getProductTrade());
            product.get().setProductSerialNumber(productDto.getProductSerialNumber());
            product.get().setProductPrice(productDto.getProductPrice());
            productRepository.save(product.get());
            return modelMapperBean.modelMapperMethod().map(product, ProductDto.class);
        }
        return null;
    }

    @Override
    public ProductDto getProductDto(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return modelMapperBean.modelMapperMethod().map(product.get(), ProductDto.class);
        }
        return null;
    }

}

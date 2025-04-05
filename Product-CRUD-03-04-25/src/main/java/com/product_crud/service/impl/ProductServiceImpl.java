package com.product_crud.service.impl;

import com.product_crud.entity.*;
import com.product_crud.exception.ResourceNotFoundException;
import com.product_crud.payload.ProductDto;
import com.product_crud.repository.*;
import com.product_crud.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        List<Category> categories = categoryRepository.findAllById(productDto.getCategoryIds());
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No valid categories found with the provided IDs");
        }
        product.getCategories().clear();
        product.getCategories().addAll(categories);

        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapToDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        // Update categories
        List<Category> categories = categoryRepository.findAllById(productDto.getCategoryIds());
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No valid categories found with the provided IDs");
        }
        product.getCategories().clear();
        product.getCategories().addAll(categories);

        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    private Product mapToEntity(ProductDto productDto) {
        List<Category> categories = categoryRepository.findAllById(productDto.getCategoryIds());
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No valid categories found with the provided IDs");
        }

        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .categories(categories)
                .build();
    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .categoryIds(product.getCategories().stream()
                        .map(Category::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
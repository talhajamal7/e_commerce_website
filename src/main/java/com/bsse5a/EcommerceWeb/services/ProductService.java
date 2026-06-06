package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.mappers.ProductMapper;
import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.models.enums.GymEquipmentCategories;
import com.bsse5a.EcommerceWeb.respositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public void createProduct(ProductDto productDto) {
        if (productDto == null) return;
        Product product = productMapper.toEntity(productDto);
        productRepository.save(product);
    }

    public List<ProductDto> searchProducts(String query) {
        return productRepository.searchProducts(query)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public Long allProductsCount() {
        return productRepository.count();
    }

    public List<ProductDto> showAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductDto dto = productMapper.toDto(product);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) return null;
        return productMapper.toDto(product.orElse(null));
    }


    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            System.out.println("Product not found by id" + id);
        }
        productRepository.deleteById(id);
    }


    public Product updateProduct(ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productDto.getId()));


        existingProduct.setTitle(productDto.getTitle());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setQuantity(productDto.getQuantity());
        existingProduct.setGymEquipmentCategories(productDto.getGymEquipmentCategories());

        if (productDto.getImageUrl() != null && !productDto.getImageUrl().trim().isEmpty()) {
            existingProduct.setImageUrl(productDto.getImageUrl());
        }

        existingProduct.setUpdatedAt(LocalDate.now());

        return productRepository.save(existingProduct);
    }

    public List<ProductDto> getProductsByCategory(GymEquipmentCategories category) {
        return productRepository.findByGymEquipmentCategories(category)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageUrl())
                .gymEquipmentCategories(product.getGymEquipmentCategories())
                .build();
    }

    public List<ProductDto> sortProducts(List<ProductDto> products, String sort) {
        if (products == null || products.isEmpty()) {
            return products;
        }

        switch (sort.toLowerCase()) {
            case "price_asc":
                return products.stream()
                        .sorted(Comparator.comparing(ProductDto::getPrice))
                        .collect(Collectors.toList());

            case "price_desc":
                return products.stream()
                        .sorted(Comparator.comparing(ProductDto::getPrice).reversed())
                        .collect(Collectors.toList());

            case "name_asc":
                return products.stream()
                        .sorted(Comparator.comparing(ProductDto::getTitle, String.CASE_INSENSITIVE_ORDER))
                        .collect(Collectors.toList());

            case "name_desc":
                return products.stream()
                        .sorted(Comparator.comparing(ProductDto::getTitle, String.CASE_INSENSITIVE_ORDER).reversed())
                        .collect(Collectors.toList());

            default:
                return products;
        }
    }


    public List<ProductDto> getTopProducts(int limit) {
        return productRepository.findAll()
                .stream()
                .limit(limit)
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}

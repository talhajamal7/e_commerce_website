package com.bsse5a.EcommerceWeb.mappers;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDto dto){
        return Product.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .gymEquipmentCategories(dto.getGymEquipmentCategories())
                .imageUrl(dto.getImageUrl())
                .quantity(dto.getQuantity())
                .build();
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .gymEquipmentCategories(product.getGymEquipmentCategories())
                .imageUrl(product.getImageUrl())
                .quantity(product.getQuantity())
                .build();
    }

}

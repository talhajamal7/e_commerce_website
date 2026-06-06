package com.bsse5a.EcommerceWeb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_title", nullable = false)
    private String productTitle;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    // Helper method to calculate subtotal
    public void calculateSubtotal() {
        this.subtotal = this.productPrice * this.quantity;
    }

    public Double getItemTotal() {
        return productPrice * quantity;
    }
}
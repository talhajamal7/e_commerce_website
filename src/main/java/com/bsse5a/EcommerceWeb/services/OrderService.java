package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.Cart;
import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.models.Order;
import com.bsse5a.EcommerceWeb.models.OrderItem;
import com.bsse5a.EcommerceWeb.respositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private static final double FREE_SHIPPING_THRESHOLD = 5000.0;
    private static final double STANDARD_SHIPPING_COST = 250.0;

    @Transactional
    public Order createOrderFromCart(Cart cart, OrderCheckoutDto checkoutDto) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("Cannot create order from empty cart");
        }

        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setCustomerName(checkoutDto.getCustomerName());
        order.setCustomerEmail(checkoutDto.getCustomerEmail());
        order.setCustomerPhone(checkoutDto.getCustomerPhone());
        order.setShippingAddress(checkoutDto.getShippingAddress());
        order.setCity(checkoutDto.getCity());
        order.setPostalCode(checkoutDto.getPostalCode());
        order.setPaymentMethod("Cash on Delivery");
        order.setNotes(checkoutDto.getNotes());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);

        double subtotal = cart.getTotal();
        double shippingCost = subtotal >= FREE_SHIPPING_THRESHOLD ? 0.0 : STANDARD_SHIPPING_COST;
        double total = subtotal + shippingCost;

        order.setSubtotal(subtotal);
        order.setShippingCost(shippingCost);
        order.setTotal(total);

        Map<Long, ProductDto> productCache = cart.getProductCache();
        for (Map.Entry<Long, Integer> entry : cart.getItems().entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();
            ProductDto product = productCache.get(productId);

            if (product != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(productId);
                orderItem.setProductTitle(product.getTitle());
                orderItem.setProductCategory(product.getGymEquipmentCategories().toString());
                orderItem.setProductImageUrl(product.getImageUrl());
                orderItem.setProductPrice(product.getPrice());
                orderItem.setQuantity(quantity);
                orderItem.calculateSubtotal();

                order.addOrderItem(orderItem);
            }
        }

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public Order getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Order not found with number: " + orderNumber));
    }

    private String generateOrderNumber() {
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomPart = String.format("%05d", new Random().nextInt(100000));
        return "ORD-" + datePart + "-" + randomPart;
    }

    public static class OrderCheckoutDto {
        private String customerName;
        private String customerEmail;
        private String customerPhone;
        private String shippingAddress;
        private String city;
        private String postalCode;
        private String notes;


        public OrderCheckoutDto() {}


        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }

        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

        public String getCustomerPhone() { return customerPhone; }
        public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

        public String getShippingAddress() { return shippingAddress; }
        public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getPostalCode() { return postalCode; }
        public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }



    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public void updateOrderStatus(Long id, Order.OrderStatus status, String notes) {
        Order order = getOrderById(id);
        order.setStatus(status);
        if (notes != null && !notes.isEmpty()) {
            order.setNotes(notes);
        }
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByCustomerEmail(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

    public long getTotalOrdersCount() {
        return orderRepository.count();
    }

    public List<Order> getRecentOrders(int limit) {
        return orderRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed()) // Sort by most recent
                .limit(limit)
                .collect(Collectors.toList());
    }
}
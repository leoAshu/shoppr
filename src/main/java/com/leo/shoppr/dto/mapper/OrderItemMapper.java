package com.leo.shoppr.dto.mapper;

import com.leo.shoppr.dto.request.CreateOrderItemRequest;
import com.leo.shoppr.dto.response.OrderItemResponse;
import com.leo.shoppr.entity.OrderItem;
import com.leo.shoppr.entity.Product;

public class OrderItemMapper {

    public OrderItem toEntity(CreateOrderItemRequest dto, Product product) {
        return OrderItem.builder()
                .product(product)
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .build();
    }

    public OrderItemResponse toDTO(OrderItem orderItem) {
        OrderItemResponse response = new OrderItemResponse();

        response.setId(orderItem.getId());
        response.setProductId(orderItem.getProduct().getId());
        response.setProductName(orderItem.getProduct().getName());
        response.setQuantity(orderItem.getQuantity());
        response.setUnitPrice(orderItem.getUnitPrice());

        return response;
    }
}

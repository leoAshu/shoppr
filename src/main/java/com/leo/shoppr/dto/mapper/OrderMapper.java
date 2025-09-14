package com.leo.shoppr.dto.mapper;

import com.leo.shoppr.dto.request.CreateOrderRequest;
import com.leo.shoppr.dto.response.OrderItemResponse;
import com.leo.shoppr.dto.response.OrderResponse;
import com.leo.shoppr.dto.response.UserResponse;
import com.leo.shoppr.entity.Order;
import com.leo.shoppr.entity.OrderItem;
import com.leo.shoppr.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public Order toEntity(CreateOrderRequest dto, User user, List<OrderItem> items) {
        Order order = Order.builder()
                .total(dto.getTotal())
                .user(user)
                .build();

        items.forEach(item -> item.setOrder(order));
        order.setItems(items);

        return order;
    }

    public OrderResponse toDTO(Order order) {
        if (order == null) return null;

        User user = order.getUser();
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());

        List<OrderItemResponse> items = order.getItems().stream().map(orderItem -> {
            OrderItemResponse item = new OrderItemResponse();
            item.setId(orderItem.getId());
            item.setProductId(orderItem.getProduct().getId());
            item.setProductName(orderItem.getProduct().getName());
            item.setQuantity(orderItem.getQuantity());
            item.setUnitPrice(orderItem.getUnitPrice());
            return item;
        }).toList();

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setStatus(order.getStatus());
        response.setTotal(order.getTotal());
        response.setCreatedAt(order.getCreatedAt());
        response.setUser(userResponse);
        response.setItems(items);

        return response;
    }
}

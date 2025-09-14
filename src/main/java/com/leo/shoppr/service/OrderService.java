package com.leo.shoppr.service;

import com.leo.shoppr.dto.mapper.OrderMapper;
import com.leo.shoppr.dto.request.CreateOrderRequest;
import com.leo.shoppr.dto.response.OrderResponse;
import com.leo.shoppr.entity.Order;
import com.leo.shoppr.entity.OrderItem;
import com.leo.shoppr.entity.Product;
import com.leo.shoppr.entity.User;
import com.leo.shoppr.exception.OrderNotFoundException;
import com.leo.shoppr.exception.UserNotFoundException;
import com.leo.shoppr.repository.OrderRepository;
import com.leo.shoppr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderResponse> getAllOrders() {
        return orderRepository
                .findAll().stream()
                .map(order -> orderMapper.toDTO(order))
                .toList();
    }

    public OrderResponse getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) throw new OrderNotFoundException(id);

        return orderMapper.toDTO(order.get());
    }

    public OrderResponse createOrder(CreateOrderRequest orderRequest) {

        User user = userRepository
                .findById(orderRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("id", orderRequest.getUserId()));

        List<OrderItem> items = orderRequest.getItems().stream()
                .map(i -> {
                    Product product = productService.getProductById(i.getProductId());
                    return OrderItem.builder()
                            .product(product)
                            .quantity(i.getQuantity())
                            .unitPrice(i.getUnitPrice())
                            .build();
                }).toList();

        Order order = orderMapper.toEntity(orderRequest, user, items);

        return orderMapper.toDTO(orderRepository.save(order));
    }
}

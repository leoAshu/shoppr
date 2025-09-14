package com.leo.shoppr.controller;

import com.leo.shoppr.dto.request.CreateOrderRequest;
import com.leo.shoppr.dto.response.OrderResponse;
import com.leo.shoppr.entity.Order;
import com.leo.shoppr.dto.common.CustomResponse;
import com.leo.shoppr.dto.common.ResponseStatus;
import com.leo.shoppr.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping
    public ResponseEntity<CustomResponse<List<OrderResponse>>> getAllOrders() {
        logger.debug("GET: /orders");

        CustomResponse<List<OrderResponse>> response = CustomResponse.<List<OrderResponse>>builder()
                .status(ResponseStatus.SUCCESS)
                .data(orderService.getAllOrders())
                .build();

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<OrderResponse>> getOrderById(@PathVariable String id) {
        logger.debug("GET: /orders/{}", id);

        CustomResponse<OrderResponse> response = CustomResponse.<OrderResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .data(orderService.getOrderById(id))
                .build();

        return  ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<OrderResponse>> createOrder(@Valid @RequestBody CreateOrderRequest order) {
        logger.debug("POST: /orders");
        logger.debug(order.toString());

        CustomResponse<OrderResponse> response = CustomResponse.<OrderResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .data(orderService.createOrder(order))
                .build();

        return ResponseEntity.ok(response);
    }

}

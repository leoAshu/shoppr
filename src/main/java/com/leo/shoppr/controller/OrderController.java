package com.leo.shoppr.controller;

import com.leo.shoppr.entity.Order;
import com.leo.shoppr.response.CustomResponse;
import com.leo.shoppr.response.ResponseStatus;
import com.leo.shoppr.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping
    public ResponseEntity<CustomResponse<List<Order>>> getAllOrders() {
        logger.debug("/orders");

        CustomResponse<List<Order>> response = CustomResponse.<List<Order>>builder()
                .status(ResponseStatus.SUCCESS)
                .data(orderService.getAllOrders())
                .build();

        List<Order> orders = orderService.getAllOrders();
        logger.debug("length: {}", orders.size());

        orderService.getAllOrders().forEach(o -> logger.debug(o.toString()));
//        logger.debug(orderService.getAllOrders());

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Order>> getOrderById(@PathVariable String id) {
        logger.debug("/orders/{}", id);

        CustomResponse<Order> response = CustomResponse.<Order>builder()
                .status(ResponseStatus.SUCCESS)
                .data(orderService.getOrderById(id))
                .build();

        return  ResponseEntity.ok(response);
    }

    // createOrder
    // updateOrder

}

package com.leo.shoppr.model;

public enum OrderStatus {
    PENDING,            // Order placed, waiting for payment confirmation
    CONFIRMED,          // Payment received, order confirmed
    PROCESSING,         // Being packed or prepared
    SHIPPED,            // Handed over to delivery carrier
    DELIVERED,          // Successfully delivered to customer
    CANCELLED,          // Cancelled before shipping
    RETURN_REQUESTED,   // Customer requested a return
    RETURNED,           // Returned and processed
    REFUNDED            // Refund issued after cancellation/return
}

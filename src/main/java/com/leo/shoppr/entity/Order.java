package com.leo.shoppr.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Positive(message = "Total should be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Total should be a valid monetary amount")
    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    protected Order() {
    }

    public Order(OrderStatus status, BigDecimal total, LocalDateTime createdAt, User user, List<OrderItem> items) {
        this.status = status;
        this.total = total;
        this.createdAt = createdAt;
        this.user = user;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", total=" + total +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", items=" + items +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderStatus status = OrderStatus.PENDING;
        private BigDecimal total;
        private LocalDateTime createdAt = LocalDateTime.now();
        private User user;
        private List<OrderItem> items = new ArrayList<>();

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder items(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(OrderItem item) {
            this.items.add(item);
            return this;
        }

        public Order build() {
            return new Order(status, total, createdAt, user, items);
        }
    }
}

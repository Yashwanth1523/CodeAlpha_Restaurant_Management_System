package com.codealpha.restaurant.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant_orders")
public class RestaurantOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String customerName;
    @Column(nullable = false)
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> items = new ArrayList<>();

    protected RestaurantOrder() {}
    public RestaurantOrder(String customerName) {
        this.customerName=customerName; this.totalAmount=0.0;
        this.status=OrderStatus.PLACED; this.createdAt=LocalDateTime.now();
    }
    public void addItem(OrderLine line) { items.add(line); line.setOrder(this); totalAmount += line.getLineTotal(); }
    public void cancel() { status=OrderStatus.CANCELLED; }
    public void setStatus(OrderStatus status) { this.status=status; }
    public Long getId(){return id;}
    public String getCustomerName(){return customerName;}
    public Double getTotalAmount(){return totalAmount;}
    public OrderStatus getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public List<OrderLine> getItems(){return items;}
}

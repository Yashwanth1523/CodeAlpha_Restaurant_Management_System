package com.codealpha.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_lines")
public class OrderLine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private RestaurantOrder order;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private Double lineTotal;

    protected OrderLine() {}
    public OrderLine(MenuItem item, int quantity) {
        this.menuItem=item; this.quantity=quantity; this.unitPrice=item.getPrice();
        this.lineTotal=unitPrice*quantity;
    }
    void setOrder(RestaurantOrder order){this.order=order;}
    public Long getId(){return id;}
    public MenuItem getMenuItem(){return menuItem;}
    public Integer getQuantity(){return quantity;}
    public Double getUnitPrice(){return unitPrice;}
    public Double getLineTotal(){return lineTotal;}
}

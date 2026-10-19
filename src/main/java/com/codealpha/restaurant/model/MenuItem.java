package com.codealpha.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String name;
    @NotBlank @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String description;
    @NotNull @DecimalMin("0.01")
    @Column(nullable = false)
    private Double price;
    @NotNull @Min(0)
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private boolean available = true;

    protected MenuItem() {}
    public MenuItem(String name, String description, Double price, Integer stock) {
        this.name=name; this.description=description; this.price=price; this.stock=stock;
        this.available = stock > 0;
    }
    public Long getId(){return id;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public Double getPrice(){return price;}
    public Integer getStock(){return stock;}
    public boolean isAvailable(){return available;}
    public void setName(String v){name=v;}
    public void setDescription(String v){description=v;}
    public void setPrice(Double v){price=v;}
    public void setStock(Integer v){stock=v; available=v>0;}
    public void reduceStock(int quantity) {
        if (quantity > stock) throw new IllegalStateException("Not enough stock for " + name);
        stock -= quantity; available = stock > 0;
    }
    public void addStock(int quantity) { stock += quantity; available = stock > 0; }
}

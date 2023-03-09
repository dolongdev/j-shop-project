package com.jshop.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productId")
    Product product;

    @ManyToOne
    @JoinColumn(name = "productColorId")
    ProductColor productColor;

    @ManyToOne
    @JoinColumn(name = "colorSizeId")
    ColorSize colorSize;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
}

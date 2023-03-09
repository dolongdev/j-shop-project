package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "product_color")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productColorId;
    private String image;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "colorId")
    Color color;

    @ManyToOne
    @JoinColumn(name = "productId")
    Product product;

    @JsonIgnore
    @OneToMany
    List<ColorSize> colorSizes;

    @JsonIgnore
    @OneToMany(mappedBy = "productColor")
    List<OrderDetail> orderDetails;
}

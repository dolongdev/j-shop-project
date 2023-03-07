package com.jshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "color_size")
public class ColorSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorSizeId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    Size size;

    @ManyToOne
    @JoinColumn(name = "productColorId")
    ProductColor productColor;
}

package com.jshop.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "colorSize")
    List<OrderDetail> orderDetails;
}

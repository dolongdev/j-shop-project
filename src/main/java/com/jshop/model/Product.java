package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    private String name;
    private Date createDate;
    private String description;
    private String detail;
    private int viewCounts;
    private float price;

    @ManyToOne
    @JoinColumn(name = "username")
    Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @JsonIgnore
    @OneToMany
    List<ProductColor> productColors;

}

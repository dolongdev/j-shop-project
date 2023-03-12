package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;
    private String codeDiscount;
    private float discount;
    private int useCount;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    List<Order> orders;
}

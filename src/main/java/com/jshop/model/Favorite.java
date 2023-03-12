package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoriteId;
    private Date createDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;
}

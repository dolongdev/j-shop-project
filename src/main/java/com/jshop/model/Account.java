package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Date createDate;

    @JsonIgnore
    @OneToMany
    List<Product> products;

    @JsonIgnore
    @OneToMany
    List<Order> orders;
}

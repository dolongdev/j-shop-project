package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    List<Authority> authorities;
}

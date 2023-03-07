package com.jshop.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "authorities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"roleId", "username"})
})
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorityId;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @ManyToOne  @JoinColumn(name = "roleId")
    private Role role;
}

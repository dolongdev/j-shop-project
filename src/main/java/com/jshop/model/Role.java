package com.jshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    private String roleId;
    private String name;

    @OneToMany(mappedBy = "role")
    List<Authority> authorities;
}

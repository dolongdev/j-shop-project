package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeId;
    private String name;
    private boolean active;

    @JsonIgnore
    @OneToMany
    List<Size> sizes;
}

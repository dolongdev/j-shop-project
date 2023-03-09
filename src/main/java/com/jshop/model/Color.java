package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorId;
    private String name;
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "color")
    List<ProductColor> productColors;
}

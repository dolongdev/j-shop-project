package com.jshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
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
    @OneToMany(mappedBy = "size")
    List<ColorSize> colorSizes;
}

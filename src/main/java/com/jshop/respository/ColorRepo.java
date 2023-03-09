package com.jshop.respository;

import com.jshop.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepo extends JpaRepository<Color, Integer> {
    Color findByNameContaining(String name);
}

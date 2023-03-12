package com.jshop.service;

import com.jshop.dto.ColorDto;
import com.jshop.model.Color;

import java.util.List;

public interface ColorService {

    List<ColorDto> findAll();
    Color findByName(String name);

    ColorDto findById(int id);
}

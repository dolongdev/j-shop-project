package com.jshop.service.impl;

import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Color;
import com.jshop.respository.ColorRepo;
import com.jshop.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepo colorRepo;

    @Override
    public Color findByName(String name) {
        Color color = this.colorRepo.findByNameContaining(name);
        return color;
    }

    @Override
    public Color findById(int id) {
        Color color = this.colorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Color", "ID", id));
        return color;
    }
}

package com.jshop.service.impl;

import com.jshop.dto.ColorDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Color;
import com.jshop.respository.ColorRepo;
import com.jshop.service.ColorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepo colorRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ColorDto> findAll() {
        List<Color> colors = this.colorRepo.findAll();

        List<ColorDto> list = colors.stream()
                .map((color) -> this.modelMapper.map(color, ColorDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public Color findByName(String name) {
        Color color = this.colorRepo.findByNameContaining(name);
        return color;
    }

    @Override
    public ColorDto findById(int id) {
        Color color = this.colorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Color", "ID", id));
        return this.modelMapper.map(color, ColorDto.class);
    }
}

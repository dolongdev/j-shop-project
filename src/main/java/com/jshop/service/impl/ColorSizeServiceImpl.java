package com.jshop.service.impl;

import com.jshop.dto.ColorSizeDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.ColorSize;
import com.jshop.respository.ColorSizeRepo;
import com.jshop.service.ColorSizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorSizeServiceImpl implements ColorSizeService {
    @Autowired
    ColorSizeRepo colorSizeRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ColorSizeDto findById(int id) {
        ColorSize colorSize = this.colorSizeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Color Size", "ID", id));
        return this.modelMapper.map(colorSize, ColorSizeDto.class);
    }
}

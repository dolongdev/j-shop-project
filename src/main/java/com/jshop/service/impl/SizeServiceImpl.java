package com.jshop.service.impl;

import com.jshop.dto.SizeDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Size;
import com.jshop.respository.SizeRepo;
import com.jshop.service.SizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepo sizeRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SizeDto> findAll() {
        List<Size> sizes = this.sizeRepo.findAll();

        List<SizeDto> list = sizes.stream()
                .map((size) -> this.modelMapper.map(size, SizeDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> getAllSizeByProc() {
        return null;
    }

    @Override
    public SizeDto findById(int id) {
        Size item = this.sizeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Size", "ID", id));
        return this.modelMapper.map(item, SizeDto.class);
    }

    @Override
    public Size findByName(String name) {
        Size size = this.sizeRepo.findByNameContaining(name);
        return size;
    }
}

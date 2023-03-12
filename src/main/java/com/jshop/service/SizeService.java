package com.jshop.service;

import com.jshop.dto.SizeDto;
import com.jshop.model.Size;

import java.util.List;

public interface SizeService {
    List<SizeDto> findAll();
    List<String> getAllSizeByProc();

    SizeDto findById(int id);

    Size findByName(String name);
}

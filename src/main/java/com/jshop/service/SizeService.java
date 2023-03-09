package com.jshop.service;

import com.jshop.model.Size;

import java.util.List;

public interface SizeService {
    List<String> getAllSizeByProc();

    Size findByName(String name);
}

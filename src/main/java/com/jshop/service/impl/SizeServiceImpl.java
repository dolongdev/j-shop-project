package com.jshop.service.impl;

import com.jshop.model.Size;
import com.jshop.respository.SizeRepo;
import com.jshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepo sizeRepo;

    @Override
    public List<String> getAllSizeByProc() {
        return null;
    }

    @Override
    public Size findByName(String name) {
        Size size = this.sizeRepo.findByNameContaining(name);
        return size;
    }
}

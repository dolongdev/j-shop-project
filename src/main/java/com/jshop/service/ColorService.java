package com.jshop.service;

import com.jshop.model.Color;

public interface ColorService {
    Color findByName(String name);

    Color findById(int id);
}

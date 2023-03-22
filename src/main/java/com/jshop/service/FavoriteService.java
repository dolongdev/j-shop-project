package com.jshop.service;

import com.jshop.dto.FavoriteDto;

import java.util.List;

public interface FavoriteService {
    FavoriteDto create(FavoriteDto item);

    FavoriteDto update(int id, FavoriteDto item);

    void delete(int id);

    void deleteByProcIdAndUsername(int productId, String username);

    FavoriteDto findById(int id);

    List<FavoriteDto> findAll();

    List<FavoriteDto> findAllByUsername(String username);

    int countByUsername(String username);
}

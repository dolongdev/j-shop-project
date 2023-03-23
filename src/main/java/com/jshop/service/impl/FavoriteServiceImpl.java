package com.jshop.service.impl;

import com.jshop.dto.FavoriteDto;
import com.jshop.dto.ProductDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Favorite;
import com.jshop.respository.FavoriteRepo;
import com.jshop.service.FavoriteService;
import com.jshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteRepo favoriteRepo;
    @Autowired
    ProductService productService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public FavoriteDto create(FavoriteDto item) {
        Favorite favorite = this.favoriteRepo.save(this.modelMapper.map(item, Favorite.class));
        return this.modelMapper.map(favorite, FavoriteDto.class);
    }

    @Override
    public FavoriteDto update(int id, FavoriteDto item) {

        return null;
    }

    @Override
    public void delete(int id) {
        FavoriteDto item = this.findById(id);
        this.favoriteRepo.delete(this.modelMapper.map(item, Favorite.class));
    }

    @Transactional
    @Override
    public void deleteByProcIdAndUsername(int productId, String username) {
        this.favoriteRepo.deleteByProduct_ProductIdAndAccount_Username(productId, username);
    }

    @Override
    public FavoriteDto findById(int id) {
        Favorite favorite = this.favoriteRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite", "ID", id));
        return this.modelMapper.map(favorite, FavoriteDto.class);
    }

    @Override
    public List<FavoriteDto> findAll() {
        List<Favorite> favorites = this.favoriteRepo.findAll();

        List<FavoriteDto> list = favorites.stream()
                .map((favorite) -> this.modelMapper
                        .map(favorite, FavoriteDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<FavoriteDto> findAllByUsername(String username) {
        List<Favorite> favorites = this.favoriteRepo.findAllByAccount_Username(username);

        List<FavoriteDto> list = favorites.stream()
                .map((favorite) -> this.modelMapper
                        .map(favorite, FavoriteDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public int countByUsername(String username) {
        int count = this.favoriteRepo.countByAccount_Username(username);
        return count;
    }

    @Override
    public List<ProductDto> top10Favorite() {
        List<ProductDto> list = new ArrayList<>();
        List<Object[]> top10FavoriteProduct = this.favoriteRepo.findTop10FavoriteProducts();
        for (int i = 0; i < top10FavoriteProduct.size(); i++) {
            ProductDto item = this.productService
                    .findById(Integer.valueOf(top10FavoriteProduct.get(i)[0].toString()));
            list.add(item);
        }
        return list;
    }
}

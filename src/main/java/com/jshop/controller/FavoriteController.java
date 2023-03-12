package com.jshop.controller;

import com.jshop.dto.FavoriteDto;
import com.jshop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @GetMapping
    public String getListFavorite(Model model){
        List<FavoriteDto> list = this.favoriteService.findAll();
        model.addAttribute("favorites", list);
        return "/home/favorites";
    }
}

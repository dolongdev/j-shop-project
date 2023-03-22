package com.jshop.controller;

import com.jshop.dto.FavoriteDto;
import com.jshop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @GetMapping
    public String getListFavorite(Model model, Principal principal){
        if (principal == null){
            model.addAttribute("message", "Vui lòng đăng nhập để truy cập trang này!");
            return "/home/login";
        }
        List<FavoriteDto> list = this.favoriteService.findAllByUsername(principal.getName());
        model.addAttribute("favorites", list);
        return "/home/favorites";
    }
}

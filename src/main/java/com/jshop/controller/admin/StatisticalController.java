package com.jshop.controller.admin;

import com.jshop.dto.ProductDto;
import com.jshop.dto.Statistical;
import com.jshop.service.AccountService;
import com.jshop.service.FavoriteService;
import com.jshop.service.OrderService;
import com.jshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administration")
public class StatisticalController {
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    FavoriteService favoriteService;

    @Autowired
    OrderService orderService;

    @GetMapping
    public String statistical(Model model){
        Statistical statistical = this.productService.getStatistical();
        List<ProductDto> top10sold = this.productService.top10sold();
        List<ProductDto> top10Favorite = this.favoriteService.top10Favorite();

        model.addAttribute("statistical", statistical);
        model.addAttribute("products", top10sold);
        model.addAttribute("productF", top10Favorite);

        return "/admin/statistical";
    }
}

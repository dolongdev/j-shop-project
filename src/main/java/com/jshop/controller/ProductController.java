package com.jshop.controller;

import com.jshop.config.AppConstants;
import com.jshop.dto.CategoryDto;
import com.jshop.dto.IdName;
import com.jshop.dto.ProductDto;
import com.jshop.model.Product;
import com.jshop.service.CategoryService;
import com.jshop.service.ProductService;
import com.jshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    SizeService sizeService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String listProc(Model model
            , @ModelAttribute(name = "pageNumber", binding = false) String pageNumber
            , @ModelAttribute(name = "choose", binding = false) String choose){

//        getAllCate(model);

        switch (choose){
            case "lowPrice" :
                getAll(model, pageNumber, "price", "asc");
                break;
            case "highPrice" :
                getAll(model, pageNumber, "price", "desc");
                break;
            default:
                getAll(model, pageNumber, "name", "asc");
        }

        return "/home/list-proc";
    }

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable int productId, Model model){
        ProductDto item = this.productService.findById(productId);
        model.addAttribute("item", item);
        List<IdName> colors = this.productService.getColors(productId);
        List<IdName> sizes = this.productService.getSizes(productId);
        if (colors.get(0).getName().equals("Không")){
            model.addAttribute("colors", null);
        }else {
            model.addAttribute("colors", colors);
        }
        if (sizes.get(0).getName().equals("Khong")){
            model.addAttribute("sizes", null);
        }else{
            model.addAttribute("sizes", sizes);
        }

        List<ProductDto> proByCate = this.productService.findAllByCategory(item.getCategory());
        model.addAttribute("proByCate", proByCate);
        List<CategoryDto> categoryDtos = this.categoryService.findAll();
        model.addAttribute("categories", categoryDtos);
        return "/home/product-detail";
    }

    @GetMapping("/sort/{sortBy}")
    public String listProcSort(Model model, @PathVariable String sortBy
            , @ModelAttribute(name = "pageNumber", binding = false) String pageNumber){
        List<ProductDto> countProc = productService.findAll();
        int maxPage = (int) Math.ceil(countProc.size() / (double) AppConstants.PAGE_SIZE);
        model.addAttribute("maxPage", maxPage);
        List<ProductDto> list;
        if (pageNumber.isEmpty() || Integer.valueOf(pageNumber) > maxPage){
            list = productService
                    .findAllSort(0
                            , AppConstants.PAGE_SIZE, sortBy, "asc");
            model.addAttribute("currentPage", 0);
            model.addAttribute("pageNumber", 0);
        }else{
            list = productService
                    .findAllSort(Integer.valueOf(pageNumber)
                            , AppConstants.PAGE_SIZE, sortBy, "asc");
            model.addAttribute("currentPage", pageNumber);
        }
        System.out.println("PageNumber: " + pageNumber + ", maxPage:" + maxPage);
        model.addAttribute("products", list);

        return "/home/list-proc";
    }

    @GetMapping("/search")
    public String listProcByName(Model model, @ModelAttribute(name = "keyword") String keyword
            , @ModelAttribute(name = "pageNumber", binding = false) String pageNumber){
        List<ProductDto> countProc = productService.findAll();
        int maxPage = (int) Math.ceil(countProc.size() / (double) AppConstants.PAGE_SIZE);
        model.addAttribute("maxPage", maxPage);
        List<ProductDto> list;
        if (pageNumber.isEmpty() || Integer.valueOf(pageNumber) > maxPage){
            list = productService
                    .searchProc(keyword, 0
                            , AppConstants.PAGE_SIZE, "name", "asc");
            model.addAttribute("currentPage", 0);
            model.addAttribute("pageNumber", 0);
        }else{
            list = productService
                    .searchProc(keyword, Integer.valueOf(pageNumber)
                            , AppConstants.PAGE_SIZE, "name", "asc");
            model.addAttribute("currentPage", pageNumber);
        }
        System.out.println("PageNumber: " + pageNumber + ", maxPage:" + maxPage);
        model.addAttribute("products", list);

        return "/home/list-proc";
    }

    private void getAll(Model model, String pageNumber, String sortBy, String sortDir){
        List<ProductDto> countProc = productService.findAll();
        int maxPage = (int) Math.ceil(countProc.size() / (double) AppConstants.PAGE_SIZE);
        model.addAttribute("maxPage", maxPage);
        List<ProductDto> list;
        if (pageNumber.isEmpty() || Integer.valueOf(pageNumber) > maxPage){
            list = productService
                    .findAllSort(0
                            , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", 0);
            model.addAttribute("pageNumber", 0);
        }else{
            list = productService
                    .findAllSort(Integer.valueOf(pageNumber)
                            , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", Integer.valueOf(pageNumber));
        }
        System.out.println("PageNumber: " + pageNumber + ", maxPage:" + maxPage);
        model.addAttribute("products", list);
    }

//    private void getAllCate(Model model){
//        List<Category> list = this.categoryService.findAll();
//        model.addAttribute("categories", list);
//    }

}
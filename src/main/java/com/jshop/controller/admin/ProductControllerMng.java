package com.jshop.controller.admin;

import com.jshop.config.AppConstants;
import com.jshop.dto.*;
import com.jshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/administration/products")
public class ProductControllerMng {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AccountService accountService;
    @Autowired
    ColorService colorService;
    @Autowired
    SizeService sizeService;

    @GetMapping
    public String getAllProducts(Model model
            , @ModelAttribute(name = "pageNumber", binding = false) String pageNumber
            , @ModelAttribute(name = "choose", binding = false) String choose
            , @ModelAttribute(name = "categoryId", binding = false) String categoryId){
        this.findAllCate(model);

        switch (choose) {
            case "lowPrice" -> getAll(model, pageNumber, "price", "asc");
            case "highPrice" -> getAll(model, pageNumber, "price", "desc");
            case "category" -> getAllByCategory(model
                    , Integer.valueOf(categoryId), pageNumber, "productId", "asc");
            default -> getAll(model, pageNumber, "productId", "asc");
        }

        return "/admin/list-proc";
    }

    @GetMapping("/addOrEdit")
    public String addOrEditProduct(Model model, Principal principal
            , @ModelAttribute(name = "productId", binding = false) String productId
            , @ModelAttribute(name = "choose", binding = false) String choose){
        this.findAllCate(model);
        this.getColorsAndSizes(model);
        AccountDto account = this.accountService.findByUsername(principal.getName());
        model.addAttribute("currentUser", account);
        if (choose.equals("update")){
            ProductDto product = this.productService.findById(Integer.valueOf(productId));
            List<CS> csList = this.productService.getSizesAndColors(Integer.valueOf(productId));
            model.addAttribute("csList", csList);
//            List<IdName> sizes = this.productService.getSizes(Integer.valueOf(productId));
//            List<IdName> colors = this.productService.getColors(Integer.valueOf(productId));
            model.addAttribute("product", product);
//            model.addAttribute("sizesById", sizes);
//            model.addAttribute("colorsById", colors);
        }else if (choose.equals("add")){
            ProductDto product = new ProductDto();
            model.addAttribute("product", product);
        }

        return "/admin/addOrEditProduct";
    }

    @PostMapping("/addOrEdit")
    public String addOrEditPost(Model model , Principal principal
            , @ModelAttribute(name = "choose") String choose
            , @ModelAttribute(name = "product") ProductDto product
            , @ModelAttribute(name = "productId", binding = false) String productId){
        if (choose.equals("add")){
            ProductDto item = this.productService.create(product);
            model.addAttribute("product", item);
        }else if (choose.equals("update")){
            ProductDto item = this.productService.update(Integer.valueOf(productId), product);
            model.addAttribute("product", item);
        }
        return null;
    }

    @PostMapping("/search")
    public String searchProc(Model model, @ModelAttribute(name = "keyword") String keyword
            , @ModelAttribute(name = "pageNumber", binding = false) String pageNumber){
        this.findAllCate(model);
        List<ProductDto> countProc = productService.findAll();
        int maxPage = (int) Math.ceil(countProc.size() / (double) AppConstants.PAGE_SIZE);
        model.addAttribute("maxPage", maxPage);
        List<ProductDto> list;
        if (pageNumber.isEmpty() || Integer.valueOf(pageNumber) > maxPage){
            list = productService
                    .searchProc(keyword, 0
                            , AppConstants.PAGE_SIZE, "productId", "asc");
            model.addAttribute("currentPage", 0);
            model.addAttribute("pageNumber", 0);
        }else{
            list = productService
                    .searchProc(keyword, Integer.valueOf(pageNumber)
                            , AppConstants.PAGE_SIZE, "productId", "asc");
            model.addAttribute("currentPage", Integer.valueOf(pageNumber));
        }
        System.out.println("PageNumber: " + pageNumber + ", maxPage:" + maxPage);
        model.addAttribute("products", list);
        return "/admin/list-proc";
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

    private void getAllByCategory(Model model, int categoryId
            , String pageNumber, String sortBy, String sortDir){
        List<ProductDto> countProc = productService.findAll();
        int maxPage = (int) Math.ceil(countProc.size() / (double) AppConstants.PAGE_SIZE);
        model.addAttribute("maxPage", maxPage);
        List<ProductDto> list;
        if (pageNumber.isEmpty() || Integer.valueOf(pageNumber) > maxPage){
            list = productService.findAllByCategorySort(categoryId, 0
                    , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", 0);
            model.addAttribute("pageNumber", 0);
        }else{
            list = productService.findAllByCategorySort(categoryId, Integer.valueOf(pageNumber)
                    , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", Integer.valueOf(pageNumber));
        }
        System.out.println("PageNumber: " + pageNumber + ", maxPage:" + maxPage);
        model.addAttribute("products", list);
    }

    private void searchProc(Model model, String keyword, String pageNumber, String sortBy, String sortDir){
        List<ProductDto> countProc = productService.findAll();
        int maxPage = (int) Math.ceil(countProc.size() / (double) AppConstants.PAGE_SIZE);
        model.addAttribute("maxPage", maxPage);
        List<ProductDto> list;
        if (pageNumber.isEmpty() || Integer.valueOf(pageNumber) > maxPage){
            list = productService
                    .searchProc(keyword, 0
                            , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", 0);
            model.addAttribute("pageNumber", 0);
        }else{
            list = productService
                    .searchProc(keyword, Integer.valueOf(pageNumber)
                            , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", Integer.valueOf(pageNumber));
        }
        System.out.println("PageNumber: " + pageNumber + ", maxPage:" + maxPage);
        model.addAttribute("products", list);
    }

    private void findAllCate(Model model){
        List<CategoryDto> categoryDtos = this.categoryService.findAll();
        model.addAttribute("categories", categoryDtos);
    }

    private void getColorsAndSizes(Model model){
        List<ColorDto> colors = this.colorService.findAll();
        List<SizeDto> sizes = this.sizeService.findAll();
        model.addAttribute("sizes", sizes);
        model.addAttribute("colors", colors);
    }
}

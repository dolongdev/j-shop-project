package com.jshop.controller.api;

import com.jshop.config.AppConstants;
import com.jshop.dto.*;
import com.jshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductColorService productColorService;
    @Autowired
    ColorSizeService colorSizeService;
    @Autowired
    ColorService colorService;
    @Autowired
    SizeService sizeService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @GetMapping
    private List<ProductDto> listProduct(){
        List<ProductDto> list = this.productService.findAll();
        return list;
    }

    @GetMapping("/checkQuantity")
    private int checkQuantity(@RequestParam int productId
            , @RequestParam int sizeId
            , @RequestParam int colorId){
        ProductColorDto productColorDto = this.productColorService.findByProductAndColor(productId, colorId);
        ColorSizeDto colorSizeDto = this.colorSizeService
                .findByProductColorAndSize(productColorDto.getProductColorId(), sizeId);
        int result = colorSizeDto.getQuantity();
        return result;
    }

    @GetMapping("/findByCate")
    private List<ProductDto> findByCate(){
        List<ProductDto> list = this
                .productService.findAllByCategorySort(2, 0
                        , AppConstants.PAGE_SIZE, "productId", "asc");
        return list;
    }

    @GetMapping("/getCS/{productId}")
    private List<CS> getCS(@PathVariable int productId){
        List<CS> list = this.productService.getSizesAndColors(productId);
        return list;
    }

    @PostMapping("/image/{productId}")
    public ResponseEntity<ProductDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer productId
    ) throws Exception {
        String fileName = this.fileService.uploadImage(path, image);
        ProductDto productDto = this.productService.findById(productId);
        productDto.setImage(fileName);
        ProductDto item = this.productService.update(productId, productDto);

        return new ResponseEntity<ProductDto>(item, HttpStatus.OK);
    }

    @GetMapping("/check/{colorId}/{sizeId}/{pId}")
    public boolean check(@PathVariable int colorId, @PathVariable int sizeId, @PathVariable int pId){
        return this.productService.checkByColorAndSize(colorId, sizeId, pId);
    }

    @PostMapping("/addPcAndCs")
    public boolean addPcAndCs(@ModelAttribute(name = "productId") String productId
            , @ModelAttribute(name = "colorId") String colorId
            , @ModelAttribute(name = "sizeId") String sizeId
            , @ModelAttribute(name = "quantity") String quantity){
        Boolean check = this.productService
                .checkByColorAndSize(Integer.valueOf(colorId), Integer.valueOf(sizeId), Integer.valueOf(productId));
        if (!check){
            ProductDto productDto = this.productService.findById(Integer.valueOf(productId));
            ProductColorDto productColorDto = new ProductColorDto();
            productColorDto.setProduct(productDto);
            productColorDto.setColor(this.colorService.findById(Integer.valueOf(colorId)));
            ProductColorDto productColorDto1 = this.productColorService.create(productColorDto);

            ColorSizeDto colorSizeDto = new ColorSizeDto();
            colorSizeDto.setQuantity(Integer.valueOf(quantity));
            colorSizeDto.setSize(this.sizeService.findById(Integer.valueOf(sizeId)));
            colorSizeDto.setProductColorDto(productColorDto1);
            ColorSizeDto colorSizeDto1 = this.colorSizeService.create(colorSizeDto);
            return true;
        }else {
            return false;
        }
    }

    @GetMapping("/top10sold")
    public ResponseEntity<List<ProductDto>> getTop10Sold(){
        List<ProductDto> list = this.productService.top10sold();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/statistical")
    public Statistical getStatistical(){
        Statistical statistical = this.productService.getStatistical();
        return statistical;
    }
}


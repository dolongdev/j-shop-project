package com.jshop.controller.api;

import com.jshop.dto.AccountDto;
import com.jshop.dto.ApiResponsive;
import com.jshop.dto.FavoriteDto;
import com.jshop.dto.ProductDto;
import com.jshop.model.Account;
import com.jshop.model.Product;
import com.jshop.service.AccountService;
import com.jshop.service.FavoriteService;
import com.jshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class ApiFavoriteController {
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<FavoriteDto>> findAll(){
        List<FavoriteDto> list = this.favoriteService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<List<FavoriteDto>> findAllByUsername(@PathVariable String username){
        List<FavoriteDto> list = this.favoriteService.findAllByUsername(username);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{favoriteId}")
    public ResponseEntity<FavoriteDto> findById(@PathVariable int favoriteId){
        FavoriteDto item = this.favoriteService.findById(favoriteId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<FavoriteDto> createFavorite(@ModelAttribute(name = "procId") int procId
            , @ModelAttribute(name = "username") String username){
        AccountDto account = this.accountService.findByUsername(username);
        ProductDto productDto = this.productService.findById(procId);
        FavoriteDto favoriteDto = new FavoriteDto(new Date(), productDto
                , account);
        FavoriteDto item = this.favoriteService.create(favoriteDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{favoriteId}")
    public ApiResponsive deleteFavorite(@PathVariable int favoriteId){
        this.favoriteService.delete(favoriteId);
        return new ApiResponsive("Xóa thành công Favorite ID" + favoriteId, true);
    }

    @DeleteMapping("/deleteByUaP/{productId}/{username}")
    public ApiResponsive deleteByProcIdAndUsername(@PathVariable int productId
            , @PathVariable String username){
        this.favoriteService.deleteByProcIdAndUsername(productId, username);
        return new ApiResponsive("Deleted Favorite with ProcId, Username: " + productId + ", " + username
                , true);
    }

    @GetMapping("/count")
    public int countByUsername(@ModelAttribute(name = "username") String username){
        int count = this.favoriteService.countByUsername(username);
        return count;
    }
}

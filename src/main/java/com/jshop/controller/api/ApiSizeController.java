package com.jshop.controller.api;

import com.jshop.model.Size;
import com.jshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sizes")
public class ApiSizeController {
    @Autowired
    SizeService sizeService;

    @GetMapping("/find")
    public ResponseEntity<Size> findByName(@ModelAttribute(name = "name") String name){
        Size size = this.sizeService.findByName(name);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
}

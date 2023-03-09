package com.jshop.controller.api;

import com.jshop.model.Color;
import com.jshop.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/colors")
public class ApiColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/find")
    public ResponseEntity<Color> findByName(@ModelAttribute(name = "name") String name){
        Color color = this.colorService.findByName(name);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }
}

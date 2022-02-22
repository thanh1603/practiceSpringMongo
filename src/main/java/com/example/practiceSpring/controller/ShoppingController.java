package com.example.practiceSpring.controller;

import com.example.practiceSpring.common.ProductByRequest;
import com.example.practiceSpring.domain.dto.ProductDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {


    @Autowired
    ShoppingService shoppingService;

//    @PostMapping("/buyProduct/{id}")
//    public ResponseEntity<ResponseObject> buyProduct(@PathVariable String id, @RequestParam double amount ) {
//        return this.shoppingService.shopping(id, amount);
//    }

    @PostMapping("/buyProduct")
    public ResponseEntity<ResponseObject> buyProduct(@RequestBody ProductByRequest productByRequest) {
        return this.shoppingService.shopping(productByRequest);
    }

}

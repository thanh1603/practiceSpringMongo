package com.example.practiceSpring.controller;

import com.example.practiceSpring.domain.dto.ProductDto;
import com.example.practiceSpring.domain.model.Product;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.reponsitory.ProductReponsitory;
import com.example.practiceSpring.security.UserDetailsImpl;
import com.example.practiceSpring.service.ProductService;
import com.example.practiceSpring.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductReponsitory productReponsitory;

    @Autowired
    ProductService productService;

//    @Autowired
//    ProductServiceImpl productServiceImpl;

    @PostMapping("/product/add")
    public ResponseEntity<ResponseObject> addProduct(@RequestBody ProductDto dto){

        return this.productService.addProduct(dto);
    }

    @GetMapping("/get/getproductsByPrice/{price}")
    public List<Product> getProductByPrice(@PathVariable Double price) {

        return this.productService.findProductByPrice(price);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductDto dto, @PathVariable String id) {
        return this.productService.updateProduct(dto,id);
    }

    @GetMapping("/get/filterProduct/{price}/{name}/{expiry}/{status}")
    public List<Product> filterProduct(@PathVariable(value = "price") Double price,
                                       @PathVariable(value = "name") String name,
                                       @PathVariable(value = "expiry") String expiry,
                                       @PathVariable(value = "status") String status
                                       ) {

        return this.productService.filterProduct(price, name, expiry, status);
    }


}

package com.example.practiceSpring.service.impl;

import com.example.practiceSpring.domain.dto.TypeProductDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.domain.model.TypeProduct;
import com.example.practiceSpring.reponsitory.TypeProductReponsitory;
import com.example.practiceSpring.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeProductServiceImpl implements TypeProductService {

    @Autowired
    TypeProductReponsitory typeProductReponsitory;

    @Override
    public ResponseEntity<ResponseObject> createTypeProduct(TypeProductDto typeProductDto){
        if (typeProductDto != null) {
            boolean check = false;
            List<TypeProduct> listTypeProduct = typeProductReponsitory.findAll();
            for (TypeProduct type : listTypeProduct) {
                if (typeProductDto.getName().equals(type.getName())){
                    check = true;
                    break;
                }
            }


//            List<TypeProduct> listResults = listTypeProduct.stream().filter(typeProduct
//                    -> Optional.ofNullable(typeProduct.getId()).isPresent() && typeProduct.getId().equals(typeProduct.getId()))
//                    .collect(Collectors.toList());
//            Optional<TypeProduct> findTypeProduct = typeProductReponsitory.findByName(typeProductDto.getName());
            if (check){
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Type of Product Available","")
                );
            }else {
                TypeProduct type = new TypeProduct();
//                type.setId(typeProductDto.getId());
                type.setName(typeProductDto.getName());
                type.setVat(typeProductDto.getVat());
                typeProductReponsitory.save(type);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "add type product success",type.getName())
                );
            }
        }
        return null;
    }
}

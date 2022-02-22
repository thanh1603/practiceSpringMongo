package com.example.practiceSpring.service.impl;

import com.example.practiceSpring.common.Constant;
import com.example.practiceSpring.common.ProductByRequest;
import com.example.practiceSpring.domain.dto.ProductDto;
import com.example.practiceSpring.domain.model.Product;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.domain.model.TypeProduct;
import com.example.practiceSpring.domain.model.User;
import com.example.practiceSpring.reponsitory.ProductReponsitory;
import com.example.practiceSpring.reponsitory.TypeProductReponsitory;
import com.example.practiceSpring.reponsitory.UserReponsitory;
import com.example.practiceSpring.security.UserDetailsImpl;
import com.example.practiceSpring.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    UserReponsitory userReponsitory;

    @Autowired
    TypeProductReponsitory typeProductReponsitory;

    @Autowired
    ProductReponsitory productReponsitory;

    @Override
    public ResponseEntity<ResponseObject> shopping(ProductByRequest productByRequest) {
        if (productByRequest != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Optional<Product> productResoult = productReponsitory.findById(productByRequest.getProducId());
            Product product = productResoult.get();
            if (product.getAmount() > productByRequest.getAmount()) {
                product.setAmount(product.getAmount() - productByRequest.getAmount());
//                product.setStatus(Constant.STATUS_PRODUCT.da_mua);
                productReponsitory.save(product);
            }

            Optional<TypeProduct> typeProduct = typeProductReponsitory.findById(product.getTypeProductId());
            TypeProduct type = typeProduct.get();
            Double vat = type.getVat();


            Optional<User> checkUser = userReponsitory.findById(userDetails.getId());
            User buyer = checkUser.get();
            Double totalMoney = (((productByRequest.getAmount())*(product.getPrice()))
                    + (vat*(productByRequest.getAmount())*(product.getPrice()))
                    - ((productByRequest.getAmount())*(product.getPrice())*(product.getDiscount())));

//            Double totalMoney = amount*product.getPrice()+vat-amount*product.getPrice()*product.getDiscount();
            if (buyer.getMoney() > totalMoney) {
                buyer.setMoney(buyer.getMoney() - totalMoney);
                buyer.setReview(Constant.STATUS_PRODUCT.da_mua);
                userReponsitory.save(buyer);
            }

            Optional<User> sellProduct = userReponsitory.findById(product.getUserId());
            User seller = sellProduct.get();
            seller.setMoney((seller.getMoney()) + totalMoney - (totalMoney*0.5));
                userReponsitory.save(seller);

//            List listSave = new ArrayList();
//            listSave.add(buyer);
//            listSave.add(seller);
//
//
//            userReponsitory.saveAll(listSave);

            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Buy product success", "")
            );

        }


        return null;
    }
}

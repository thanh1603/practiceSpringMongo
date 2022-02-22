package com.example.practiceSpring.service.impl;

import com.example.practiceSpring.common.Constant;
import com.example.practiceSpring.domain.dto.ProductDto;
import com.example.practiceSpring.domain.dto.UserDto;
import com.example.practiceSpring.domain.model.Product;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.domain.model.User;
import com.example.practiceSpring.reponsitory.ProductReponsitory;
import com.example.practiceSpring.reponsitory.UserReponsitory;
import com.example.practiceSpring.security.UserDetailsImpl;
import com.example.practiceSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ProductReponsitory productReponsitory;

    @Autowired
    UserReponsitory userReponsitory;

    @Override
    public ResponseEntity<ResponseObject> addProduct(ProductDto dto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (dto != null) {
            Optional<User> checkUser = userReponsitory.findById(userDetails.getId());
            Optional<String> role = Optional.ofNullable(checkUser).map(rl -> rl.get().getRole());
            if (role.isPresent()) {
                String checkRole = role.get();
                if (Constant.ROLE.ctv.equalsIgnoreCase(checkRole) || Constant.ROLE.admin.equalsIgnoreCase(checkRole)) {
                    Product product = new Product();
//            product.setId(dto.getId());
                    product.setName(dto.getName());
                    product.setTypeProductId(dto.getTypeProductId());
                    product.setPrice(dto.getPrice());
                    product.setDiscount(dto.getDiscount());
                    product.setExpiry(dto.getExpiry());
                    product.setStatus(dto.getStatus());
                    product.setUserId(userDetails.getId());
                    this.productReponsitory.save(product);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "Add product success", product.getName())
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "you do not have access", "")
                    );
                }
            }

        }
        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> updateProduct(ProductDto dto, String id) {
        if (dto != null && id != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> userResult = userReponsitory.findById(userDetails.getId());
            Optional<String> opt = Optional.ofNullable(userResult).map(pd -> pd.get().getRole());
            if (opt.isPresent()) {
                String checkRole = opt.get();
                if (Constant.ROLE.admin.equalsIgnoreCase(checkRole) || Constant.ROLE.admin.equalsIgnoreCase(checkRole)) {
                    Optional<Product> product = productReponsitory.findById(id);
                    Product update = product.get();
                    update.setPrice(dto.getPrice());
                    update.setDiscount(dto.getDiscount());

                    productReponsitory.save(update);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "Update success", update)
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                            new ResponseObject("failed", "you do not have access", ""));
                }
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "data not found", ""));

        }
        return null;

    }

    @Override
    public List<Product> findProductByPrice(Double price) {
        if (price != null) {
            List<Product> listProducts = productReponsitory.findAll();
            List<Product> listResults = listProducts.stream().filter(product
                    -> Optional.ofNullable(product.getPrice()).isPresent() && product.getPrice().equals(price))
                    .collect(Collectors.toList());

            return listResults;
        }
        return null;
    }

    @Override
    public List<Product> filterProduct(Double price, String name, String expiry, String status) {

//       return this.productReponsitory.filterProduct(price, name, expiry, status);

        Query query = new Query();
        Criteria criteria = Criteria.where(Constant.PRODUCT.PRICE).is(price)
                .and(Constant.PRODUCT.NAME).is(name)
                .and(Constant.PRODUCT.EXPIRY).is(expiry)
                .and(Constant.PRODUCT.STATUS).is(status);

        query.addCriteria(criteria);
        return mongoTemplate.find(query, Product.class);

    }

    @Override
    public ResponseEntity<ResponseObject> checkExpiryProduct(String expiry) {
        if (expiry != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> userResult = userReponsitory.findById(userDetails.getId());
            Optional<String> opt = Optional.ofNullable(userResult).map(user -> user.get().getRole());
            if (opt.isPresent()) {
                String role = opt.get();
                if (Constant.ROLE.admin.equalsIgnoreCase(role)) {
                    List<Product> listResults = productReponsitory.findAll();
                    for (Product pd : listResults) {
                        if (pd.getExpiry().equalsIgnoreCase(expiry)) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                            LocalDate date = LocalDate.parse(expiry, formatter);
                            long millis = date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
                            long millisNow = System.currentTimeMillis();
                            if (millisNow > millis && (
                                    pd.getStatus().equalsIgnoreCase(Constant.STATUS_PRODUCT.con_hang) ||
                                            pd.getStatus().equalsIgnoreCase(Constant.STATUS_PRODUCT.hang_moi))) {
                                pd.setDiscount(0.9);
                                pd.setStatus(Constant.STATUS_PRODUCT.loai_bo);
                                productReponsitory.save(pd);
                            }

                        }
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "successful processing", "")
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                            new ResponseObject("failed", "you do not have access", ""));
                }
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", "please data transmission", ""));
    }


}

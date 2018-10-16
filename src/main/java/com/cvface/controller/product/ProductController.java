package com.cvface.controller.product;

import com.cvface.model.product.Product;
import com.cvface.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value="/product")
public class ProductController {
    @Autowired
    ProductService productService;
    /**
     * 获取产品信息
     * @return
     */
    @RequestMapping(path = "/getProducts")
    public ResponseEntity<Object> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @RequestMapping(path = "/findAllProducts")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }
}

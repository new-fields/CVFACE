package com.cvface.service.product.impl;

import com.cvface.dao.product.ProductDao;
import com.cvface.model.product.Product;
import com.cvface.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao productDao;

    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setProductId("1");
        honey.setProductName("Honey");
        productRepo.put(honey.getProductId(), honey);

        Product almond = new Product();
        almond.setProductId("2");
        almond.setProductName("Almond");
        productRepo.put(almond.getProductId(), almond);
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateProduct(String id, Product product) {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public Collection<Product> getProducts() {
        return productRepo.values();
    }

    @Override
    public List<Product> findAllProducts() {
        try {
            return productDao.findAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.cvface.dao.product;

import com.cvface.model.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    public List<Product> findAllProducts() throws Exception;
}

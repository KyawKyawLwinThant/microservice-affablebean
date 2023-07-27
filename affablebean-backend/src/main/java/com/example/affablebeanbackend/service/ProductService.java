package com.example.affablebeanbackend.service;

import com.example.affablebeanbackend.dao.CategoryDao;
import com.example.affablebeanbackend.dao.ProductDao;
import com.example.affablebeanbackend.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public Products listAllProduct(){
        return new Products(productDao.findAll().spliterator());
    }

}

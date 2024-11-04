package com.ra.modul04.model.service.product;

import com.ra.modul04.model.entity.Product;

import java.util.List;

public interface ProductService {
List<Product> findAll();
Product save(Product product);
Product update(Product product);
void delete(Long id);
Product findById(Long id);
}

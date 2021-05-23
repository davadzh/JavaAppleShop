package com.davadzh.bluebeard.BLL.Services.ProductService;

import com.davadzh.bluebeard.DAL.Category.Category;
import com.davadzh.bluebeard.DAL.Product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    List<Product> GetProductsByCategoryId(Long id);
    Product GetProductById(Long id);
    String DeleteProduct(Long id);
}

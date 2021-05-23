package com.davadzh.bluebeard.BLL.Services.ProductService;

import com.davadzh.bluebeard.BLL.Constants.ExceptionMessages;
import com.davadzh.bluebeard.BLL.Exceptions.BadRequestException;
import com.davadzh.bluebeard.BLL.Exceptions.NotFoundException;
import com.davadzh.bluebeard.DAL.Category.CategoryRepository;
import com.davadzh.bluebeard.DAL.Product.Product;
import com.davadzh.bluebeard.DAL.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setProductService(CategoryRepository categoryRepository,
                                  ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> GetProductsByCategoryId(Long id) {
        var category = categoryRepository.findById(id);

        if (category.isEmpty())
            throw new NotFoundException("Категории не существует");

        return category.get().getProducts();
    }

    @Override
    public Product GetProductById(Long id) {
        var product = productRepository.findById(id);

        if(product.isEmpty())
            throw new NotFoundException("Товар не найден");

        return product.get();
    }

    @Override
    public String DeleteProduct(Long id) {
        var product = productRepository.findById(id);

        if(product.isEmpty())
            throw new NotFoundException("Товар не найден");
        else
            productRepository.deleteById(id);

        return "Товар успешно удален";
    }
}

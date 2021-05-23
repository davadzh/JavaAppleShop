package com.davadzh.bluebeard.BLL.Services.CategoryService;

import com.davadzh.bluebeard.BLL.Exceptions.NotFoundException;
import com.davadzh.bluebeard.DAL.Category.Category;
import com.davadzh.bluebeard.DAL.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void SetCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> GetCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category GetCategoryById(Long id) {
        var category = categoryRepository.findById(id);

        if(category.isEmpty())
            throw new NotFoundException("Категория не найдена");

        return category.get();
    }

    @Override
    public String DeleteCategory(Long id) {
        var category = categoryRepository.findById(id);

        if(category.isEmpty())
            throw new NotFoundException("Категория не найдена");
        else
            categoryRepository.deleteById(id);

        return "Категория успешно удалена";
    }
}

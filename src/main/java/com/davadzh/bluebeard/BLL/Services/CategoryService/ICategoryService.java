package com.davadzh.bluebeard.BLL.Services.CategoryService;
import com.davadzh.bluebeard.DAL.Category.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    List<Category> GetCategories();
    Category GetCategoryById(Long id);
    String DeleteCategory(Long id);
}

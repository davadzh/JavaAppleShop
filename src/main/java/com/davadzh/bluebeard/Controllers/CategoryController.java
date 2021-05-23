package com.davadzh.bluebeard.Controllers;

import com.davadzh.bluebeard.BLL.Core.Response;
import com.davadzh.bluebeard.BLL.Services.CategoryService.ICategoryService;
import com.davadzh.bluebeard.DAL.Category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private ICategoryService categoryService;

    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategories")
    Response<List<Category>> getCategories() {
        var categories = categoryService.GetCategories();

        return new Response<>(categories);
    }

    @PostMapping("/getCategoryById")
    Response<Category> getCategoryById(@RequestBody Long id) {
        var category = categoryService.GetCategoryById(id);

        return new Response<>(category);
    }

    @DeleteMapping("/deleteCategory")
    Response<String> deleteCategory(@RequestBody Long id) {
        var message = categoryService.DeleteCategory(id);

        return new Response<>(message);
    }
}

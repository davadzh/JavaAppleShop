package com.davadzh.bluebeard.Controllers;

import com.davadzh.bluebeard.BLL.Core.Response;
import com.davadzh.bluebeard.BLL.Services.ProductService.IProductService;
import com.davadzh.bluebeard.DAL.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private IProductService productService;

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProductsByCategoryId")
    Response<List<Product>> getProductsByCategoryId(@RequestParam Long id) {
        var products = productService.GetProductsByCategoryId(id);

        return new Response<>(products);
    }

    @PostMapping("/getProductById")
    Response<Product> getProductById(@RequestBody Long id) {
        var product = productService.GetProductById(id);

        return new Response<>(product);
    }

    @DeleteMapping("/deleteProduct")
    Response<String> deleteProduct(@RequestBody Long id) {
        var message = productService.DeleteProduct(id);

        return new Response<>(message);
    }
}

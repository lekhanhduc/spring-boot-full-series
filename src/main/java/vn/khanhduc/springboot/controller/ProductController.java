package vn.khanhduc.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.khanhduc.springboot.model.Product;
import vn.khanhduc.springboot.service.ProductService;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Long createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.findAll();
    }

}

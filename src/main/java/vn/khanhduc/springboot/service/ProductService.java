package vn.khanhduc.springboot.service;

import org.springframework.stereotype.Service;
import vn.khanhduc.springboot.model.Product;
import vn.khanhduc.springboot.repository.ProductRepository;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(Product product) {
        Product save = productRepository.save(product);
        return save.getId();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

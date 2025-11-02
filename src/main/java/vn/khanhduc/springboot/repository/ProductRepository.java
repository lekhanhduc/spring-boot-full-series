package vn.khanhduc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.khanhduc.springboot.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

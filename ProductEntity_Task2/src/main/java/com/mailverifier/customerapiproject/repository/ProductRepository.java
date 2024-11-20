package com.mailverifier.customerapiproject.repository;
import com.mailverifier.customerapiproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query to fetch top 2 products within the price range 15 to 30, ordered by price
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price ASC")
    List<Product> findTop2ProductsByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
}

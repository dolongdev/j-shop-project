package com.jshop.respository;

import com.jshop.model.Category;
import com.jshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategory(Category category);

    Page<Product> findByNameContaining(String tittle, Pageable pageable);

    @Procedure(name = "get_colors_by_id")
    List<String> get_colors_by_id(@Param("id") int id);

    @Procedure(name = "get_sizes_by_id")
    List<String> get_sizes_by_id(@Param("id") int id);

}

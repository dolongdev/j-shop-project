package com.jshop.respository;

import com.jshop.dto.IdName;
import com.jshop.dto.Statistical;
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

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    Page<Product> findByNameContaining(String tittle, Pageable pageable);

    @Procedure(name = "get_colors_by_id")
    List<Object[]> get_colors_by_id(@Param("id") int id);

    @Procedure(name = "get_sizes_by_id")
    List<Object[]> get_sizes_by_id(@Param("id") int id);

    @Procedure(name = "get_cs_product")
    List<Object[]> get_cs_product(@Param("id") int id);

    @Procedure(name = "top10sold")
    List<Product> top10sold();

    @Procedure(name = "findByPcAndCs")
    Product findByPcAndCs(@Param("pcId") int pcId, @Param("csId") int csId);

    @Procedure(name = "checkByColorAndSize")
    Product checkByColorAndSize(@Param("cId") int cId
            , @Param("sId") int sId, @Param("pId") int pId);

    @Procedure(name = "statistical")
    List<Object[]> statistical();
}

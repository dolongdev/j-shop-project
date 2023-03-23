package com.jshop.respository;

import com.jshop.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepo extends JpaRepository<Favorite, Integer> {
    int countByAccount_Username(String username);

    List<Favorite> findAllByAccount_Username(String username);

    @Query(value = "SELECT f.product_id, COUNT(*) " +
            "as favorite_count FROM Favorites f GROUP BY f.product_id ORDER BY favorite_count DESC",
     nativeQuery = true)
    List<Object[]> findTop10FavoriteProducts();

    void deleteByProduct_ProductIdAndAccount_Username(int id, String username);
}

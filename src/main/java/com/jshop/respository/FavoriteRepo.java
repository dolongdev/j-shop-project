package com.jshop.respository;

import com.jshop.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepo extends JpaRepository<Favorite, Integer> {
    int countByAccount_Username(String username);

    List<Favorite> findAllByAccount_Username(String username);

    void deleteByProduct_ProductIdAndAccount_Username(int id, String username);
}

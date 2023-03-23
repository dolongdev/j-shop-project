package com.jshop.respository;

import com.jshop.model.Authority;
import com.jshop.model.ColorSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
    Authority findByAccount_Username(String username);

    <S extends ColorSize> S save(S entity);
}

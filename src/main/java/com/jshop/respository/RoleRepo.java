package com.jshop.respository;

import com.jshop.model.ColorSize;
import com.jshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
    <S extends Role> S save(S entity);
}

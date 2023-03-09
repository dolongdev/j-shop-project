package com.jshop.respository;

import com.jshop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SizeRepo extends JpaRepository<Size, Integer> {
    Size findByNameContaining(String name);
}

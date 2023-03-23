package com.jshop.respository;

import com.jshop.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface AccountRepo extends JpaRepository<Account, String> {
    @Procedure(name = "deleteAccount")
    void deleteAccount(@Param("username") String username);
}

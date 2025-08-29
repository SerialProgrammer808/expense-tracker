package com.noahasano.expense.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.noahasano.expense.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

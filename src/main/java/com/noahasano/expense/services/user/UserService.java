package com.noahasano.expense.services.user;

import com.noahasano.expense.dto.RegisterRequest;
import com.noahasano.expense.dto.UserDTO;
import com.noahasano.expense.entity.User;

public interface UserService {
    
    User registerUser(RegisterRequest registerRequest);
    User findByUsername(String username);
    UserDTO getUserProfile(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

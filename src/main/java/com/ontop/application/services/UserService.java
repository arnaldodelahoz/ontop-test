package com.ontop.application.services;

import com.ontop.application.dtos.UserDTO;
import com.ontop.application.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long userId) throws ResourceNotFoundException;
    Page<UserDTO> getAllUsers(int page, int size);
    UserDTO updateUser(Long userId, UserDTO userDTO) throws ResourceNotFoundException;
    void deleteUser(Long userId) throws ResourceNotFoundException;
}

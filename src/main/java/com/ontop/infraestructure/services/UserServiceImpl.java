package com.ontop.infraestructure.services;

import com.ontop.application.dtos.UserDTO;
import com.ontop.application.exceptions.ResourceNotFoundException;
import com.ontop.application.mappers.UserMapper;
import com.ontop.application.services.UserService;
import com.ontop.domain.entities.User;
import com.ontop.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(user.getPassword());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userMapper.toDTO(user);
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::toDTO);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
    }
}



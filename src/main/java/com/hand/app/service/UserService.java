package com.hand.app.service;

import com.hand.api.controller.DTO.UserDTO;
import com.hand.domain.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);
    void delete(Long userId);

    List<UserDTO> exportData(UserDTO userDTO);
}

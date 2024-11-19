package com.hand.app.service;

import com.hand.domain.entity.User;

public interface UserService {
    User create(User user);
    void delete(Long userId);
}

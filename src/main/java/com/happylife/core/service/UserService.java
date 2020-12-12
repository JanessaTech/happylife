package com.happylife.core.service;

import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.mbg.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User selectByPrimaryKey(UUID userId);
    List<User> getUsersByFilter(UserFilter userFilter);
    void createUser(User user);
    User updateUser(User user);
    void deleteUserById(UUID userId);
}

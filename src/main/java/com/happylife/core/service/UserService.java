package com.happylife.core.service;

import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.exception.uuid.UUIDException;
import com.happylife.core.mbg.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId);
    List<User> getUsersByFilter(UserFilter userFilter) throws UUIDException;
    int createUser(User user);
    int updateUser(User user);
    int deleteUserById(UUID userId);
    int deleteUsersByIds(List<Object> uuids);
}

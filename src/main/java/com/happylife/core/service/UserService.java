package com.happylife.core.service;

import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.exception.user.UserException;
import com.happylife.core.exception.uuid.UUIDException;
import com.happylife.core.mbg.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId) throws UserException;
    List<User> getUsersByFilter(UserFilter userFilter) throws UserException;
    int createUser(User user) throws UserException;
    int updateUser(User user) throws UserException;
    int deleteUserById(UUID userId) throws UserException;
    int deleteUsersByIds(List<Object> uuids) throws UserException;
}

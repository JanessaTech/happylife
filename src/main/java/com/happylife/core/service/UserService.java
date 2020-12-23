package com.happylife.core.service;

import com.github.pagehelper.PageInfo;
import com.happylife.core.dto.user.UserProfileFilter;
import com.happylife.core.exception.user.UserProfileException;
import com.happylife.core.mbg.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId) throws UserProfileException;
    PageInfo<User> getUsersByFilter(UserProfileFilter userProfileFilter, int page, int pageSize) throws UserProfileException;
    User getUsersByName(String name) throws UserProfileException;
    int createUser(User user) throws UserProfileException;
    int updateUser(User user) throws UserProfileException;
    int deleteUserById(UUID userId) throws UserProfileException;
    int deleteUsersByIds(List<Object> uuids) throws UserProfileException;
}

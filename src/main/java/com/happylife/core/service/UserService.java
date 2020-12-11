package com.happylife.core.service;

import com.happylife.core.mbg.model.User;

import java.util.UUID;

public interface UserService {
    User selectByPrimaryKey(UUID userId);
}

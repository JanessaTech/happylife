package com.happylife.core.service;

import com.happylife.core.dto.user.CustomUser;
import com.happylife.core.dto.user.UserEntity;
import com.happylife.core.exception.user.UserProfileException;
import com.happylife.core.mbg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CustomUser loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = null;
        try{
            user = userService.getUsersByName(name);
        }catch (UserProfileException ex){
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
        CustomUser customUser = null;
        if(user != null){
            UserEntity userEntity = new UserEntity(user);
            customUser = new CustomUser(userEntity);
            return customUser;
        }
        throw new UsernameNotFoundException("cannot find user by name:" + name);
    }
}

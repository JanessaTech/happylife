package com.happylife.core.service.imp;

import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.mbg.mappers.UserMapper;
import com.happylife.core.mbg.model.User;
import com.happylife.core.mbg.model.UserExample;
import com.happylife.core.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(UUID userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getUsersByFilter(UserFilter userFilter) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!userFilter.getUserIds().equals("") ){
            String[] userIdArr = userFilter.getUserIds().split(",");
            List<Object> uuids = new ArrayList<>();
            for(String userId : userIdArr){
                uuids.add(UUID.fromString(userId));
            }
            criteria.andUserIdIn(uuids);
        }
        if(!userFilter.getName().equals("")){
            criteria.andNameEqualTo(userFilter.getName());
        }
        if(!userFilter.getSex().equals("")){
            criteria.andSexEqualTo(userFilter.getSex());
        }
        if(!userFilter.getSortby().equals("") && !userFilter.getOrder().equals("")){
            userExample.setOrderByClause(userFilter.getSortby() + " " + userFilter.getOrder());
        }

        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public void createUser(User user) {
        userMapper.insert(user);
        System.out.println(String.format("User %s is inserted successfully", user.getUserId()));
    }

    @Override
    public User updateUser(User user) {
        this.userMapper.updateByPrimaryKey(user);
        return user;
    }

    @Override
    public void deleteUserById(UUID userId) {
        this.userMapper.deleteByPrimaryKey(userId);
    }
}

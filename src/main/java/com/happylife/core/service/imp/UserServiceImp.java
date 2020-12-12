package com.happylife.core.service.imp;

import com.happylife.core.common.UUIDGenerator;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.mbg.mappers.UserMapper;
import com.happylife.core.mbg.model.User;
import com.happylife.core.mbg.model.UserExample;
import com.happylife.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


@Service
public class UserServiceImp implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
    @Autowired
    private MessageSource messageSource;

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(UUID userId) {
        logger.debug("selectByPrimaryKey by userId:" + userId);
        return userMapper.selectByPrimaryKey(userId);
    }

    private List<Object> convertType2Obj(List<UUID> uuids){
        List<Object> uuidObjs = new ArrayList<>();
        uuidObjs.addAll(uuids);
        return uuidObjs;
    }

    @Override
    public List<User> getUsersByFilter(UserFilter userFilter) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!userFilter.getUserIds().equals("") ){
            List<UUID> uuids = UUIDGenerator.getUUIDs(userFilter.getUserIds());
            List<Object> uuidObjs = convertType2Obj(uuids);
            criteria.andUserIdIn(uuidObjs);
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
        logger.info(this.messageSource.getMessage("user.create", new Object[]{user.getUserId().toString()}, Locale.getDefault()));
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

    @Override
    public void deleteUsersByIds(List<UUID> uuids) {
        UserExample userExample = new UserExample();
        List<Object> uuidObjs = convertType2Obj(uuids);
        userExample.createCriteria().andUserIdIn(uuidObjs);
        this.userMapper.deleteByExample(userExample);
    }
}

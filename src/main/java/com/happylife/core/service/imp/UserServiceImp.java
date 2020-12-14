package com.happylife.core.service.imp;

import com.happylife.core.common.UUIDGenerator;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.exception.uuid.UUIDException;
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
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImp implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UUIDGenerator uuidGenerator;

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(UUID userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getUsersByFilter(UserFilter userFilter) throws UUIDException {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!userFilter.getUserIds().equals("") ){
            List<Object> uuids = uuidGenerator.getUUIDs(userFilter.getUserIds());
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
    public int createUser(User user) {
        int res = userMapper.insert(user);
        return res;
    }

    @Override
    public int updateUser(User user) {
        int res = this.userMapper.updateByPrimaryKey(user);
        return res;
    }

    @Override
    public int deleteUserById(UUID userId) {
        int res = this.userMapper.deleteByPrimaryKey(userId);
        return res;
    }

    @Override
    public int deleteUsersByIds(List<Object> uuids) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIn(uuids);
        int res  = this.userMapper.deleteByExample(userExample);
        return res;
    }
}

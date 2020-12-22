package com.happylife.core.service.imp;

import com.happylife.core.common.UUIDGenerator;
import com.happylife.core.dto.user.UserFilter;
import com.happylife.core.exception.user.UserProfileException;
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
import java.util.Locale;
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
    public User getUserById(UUID userId) throws UserProfileException {
        User user = null;
        try{
            user = userMapper.selectByPrimaryKey(userId);
        }catch(Exception ex){
            throw new UserProfileException(ex.getMessage(), ex);
        }
        return user;
    }

    @Override
    public List<User> getUsersByFilter(UserFilter userFilter) throws UserProfileException {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!userFilter.getUserIds().equals("") ){
            List<Object> uuids = null;
            try{
                 uuids = uuidGenerator.getUUIDs(userFilter.getUserIds());
            }catch (UUIDException ex){
                throw new UserProfileException(ex.getMessage(), ex);
            }

            criteria.andUserIdIn(uuids);
        }
        if(!userFilter.getName().equals("")){
            criteria.andNameEqualTo(userFilter.getName());
        }
        if(!userFilter.getPassword().equals("")){
            criteria.andPasswordEqualTo(userFilter.getPassword());
        }
        if(!userFilter.getSex().equals("")){
            criteria.andSexEqualTo(userFilter.getSex());
        }
        if(!userFilter.getSortby().equals("") && !userFilter.getOrder().equals("")){
            userExample.setOrderByClause(userFilter.getSortby() + " " + userFilter.getOrder());
        }

        List<User> users = null;
        try{
             users = userMapper.selectByExample(userExample);
        }catch(Exception ex){
            throw new UserProfileException(ex.getMessage(),ex);
        }
        return users;
    }

    @Override
    public User getUsersByName(String name) throws UserProfileException {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(name != null && !name.equals("")){
            criteria.andNameEqualTo(name);
        }

        List<User> users= null;
        try{
            users = userMapper.selectByExample(userExample);
        }catch (Exception ex){
            throw new UserProfileException(ex.getMessage(), ex);
        }
        if(users == null || users.size() == 0){
            String msg = this.messageSource.getMessage("user.notfound.byname",new Object[]{name}, Locale.getDefault());
            throw new UserProfileException(msg);
        }
        return users.get(0);
    }

    @Override
    public int createUser(User user) throws UserProfileException {
        int res = 0;
        try{
            res = userMapper.insert(user);
        }catch (Exception ex){
            throw new UserProfileException(ex.getMessage(), ex);
        }
        return res;
    }

    @Override
    public int updateUser(User user) throws UserProfileException {
        int res = 0;
        try{
            res = this.userMapper.updateByPrimaryKey(user);
        }catch(Exception ex){
            throw new UserProfileException(ex.getMessage(), ex);
        }
        return res;
    }

    @Override
    public int deleteUserById(UUID userId) throws UserProfileException {
        int res = 0;
        try{
            res = this.userMapper.deleteByPrimaryKey(userId);
        }catch (Exception ex){
            throw new UserProfileException(ex.getMessage(), ex);
        }
        return res;
    }

    @Override
    public int deleteUsersByIds(List<Object> uuids) throws UserProfileException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIn(uuids);
        int res = 0;
        try{
            res  = this.userMapper.deleteByExample(userExample);
        }catch(Exception ex){
            throw new UserProfileException(ex.getMessage(), ex);
        }
        return res;
    }
}

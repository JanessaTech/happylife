package com.happylife.core.service.imp;

import com.happylife.core.exception.user.UserProfileException;
import com.happylife.core.mbg.mappers.UserMapper;
import com.happylife.core.mbg.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserExternalClass {
    @Resource
    private UserMapper userMapper;

    public void functionWithOutTransaction(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by functionWithOutTransaction in UserExternalClass");
    }

    @Transactional
    public void functionWithTransaction(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by functionWithTransaction in UserExternalClass");
    }

    @Transactional(rollbackFor = UserProfileException.class)
    public void functionWithTransactionrollbackForUserProfileException(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by functionWithTransactionrollbackForUserProfileException in UserExternalClass");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void functionWithTransactionPropagationREQUIRES_NEW(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by functionWithTransactionPropagationREQUIRES_NEW in UserExternalClass");
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = UserProfileException.class)
    public void functionWithTransactionPropagationREQUIRES_NEWrollbackForUserProfileException(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by functionWithTransactionPropagationREQUIRES_NEWrollbackForUserProfileException in UserExternalClass");
    }
}

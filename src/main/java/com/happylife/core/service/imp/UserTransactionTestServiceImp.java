package com.happylife.core.service.imp;

import com.happylife.core.exception.MyOwnTransactionException;
import com.happylife.core.exception.user.UserProfileException;
import com.happylife.core.mbg.mappers.UserMapper;
import com.happylife.core.mbg.model.User;
import com.happylife.core.service.UserTransactionTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserTransactionTestServiceImp implements UserTransactionTestService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserExternalClass userExternalClass;

    @Resource
    private UserMapper userMapper;

    /**
     * [V]  all users roll back
     * @param users
     */
    @Transactional
    @Override
    public void case1(List<User> users) {
        this.userMapper.insert(users.get(0));
        this.userMapper.insert(users.get(1));
        throw new RuntimeException("RuntimeException throw by case1");
    }

    /**
     * [X] all users didn't roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional
    @Override
    public void case2(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by case2");
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = UserProfileException.class)
    @Override
    public void case3(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by case3");
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case4(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by case4");
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case5(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithoutTransactional(users);
    }

    private void innerFunctionWithoutTransactional(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by innerFunctionWihtoutTransactional");
    }

    @Transactional
    private void innerFunctionWithTransactional(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by innerFunctionWithTransactional");
    }

    @Transactional(rollbackFor = UserProfileException.class)
    private void innerFunctionWithTransactionalRollbackForUserProfileException(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by innerFunctionWithTransactionalRollbackForUserProfileException");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void innerFunctionWithTransactionalPropagationREQUIRES_NEW(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by innerFunctionWithTransactionalPropagationREQUIRES_NEW");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = UserProfileException.class)
    private void  innerFunctionWithTransactionalPropagationREQUIRES_NEWrollbackForUserProfileException(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(1));
        throw new UserProfileException("UserProfileException throw by innerFunctionWithTransactionalPropagationREQUIRES_NEWrollbackForUserProfileException");
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case6(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactional(users);
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case7(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactionalRollbackForUserProfileException(users);
    }

    /**
     *[V] all users roll back
     * reason:
     * In the same class, Transactional label of inner caller will be ignored
     * Transactional label of outsider caller only takes affect
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case8(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactionalPropagationREQUIRES_NEW(users);
    }


    /**
     * [V] all users roll back
     * same reason as case8
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case9(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactionalPropagationREQUIRES_NEWrollbackForUserProfileException(users);
    }

    /**
     * [X] all users didn't roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = MyOwnTransactionException.class)
    @Override
    public void case10(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactional(users);
    }

    /**
     * [X] all users didn't roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = MyOwnTransactionException.class)
    @Override
    public void case11(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactionalRollbackForUserProfileException(users);
    }

    /**
     * [X] all users didn't roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = MyOwnTransactionException.class)
    @Override
    public void case12(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactionalPropagationREQUIRES_NEW(users);
    }

    /**
     * [X] all users didn't roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = MyOwnTransactionException.class)
    @Override
    public void case13(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        innerFunctionWithTransactionalPropagationREQUIRES_NEWrollbackForUserProfileException(users);
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case14(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            innerFunctionWithoutTransactional(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case15(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            innerFunctionWithTransactional(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * Pay attention to this case.
     * The reason:
     * the second transaction joined into first transaction
     * the first transaction thought there was no exception since the exception thrown by inner was caught in case16,
     * then it committed
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case16(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            innerFunctionWithTransactionalRollbackForUserProfileException(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case17(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            innerFunctionWithTransactionalPropagationREQUIRES_NEW(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case18(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            innerFunctionWithTransactionalPropagationREQUIRES_NEWrollbackForUserProfileException(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Override
    public void case19(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            innerFunctionWithTransactionalRollbackForUserProfileException(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Override
    public void case20(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            innerFunctionWithTransactionalPropagationREQUIRES_NEWrollbackForUserProfileException(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }


    // call outside

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case21(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithOutTransaction(users);
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case22(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransaction(users);
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case23(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransactionrollbackForUserProfileException(users);
    }

    /**
     * [X] user1 roll back, user2 didn't roll back:
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case24(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransactionPropagationREQUIRES_NEW(users);
    }

    /**
     * [V] all users roll back
     * @param users
     * @throws UserProfileException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case25(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransactionPropagationREQUIRES_NEWrollbackForUserProfileException(users);
    }

    /**
     *  [X] all users didn't roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case26(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            this.userExternalClass.functionWithOutTransaction(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case27(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            this.userExternalClass.functionWithTransaction(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [V] all users roll back
     * please make comparision with case 16
     * The reason:
     * Transaction rolled back because it has been marked as rollback-only
     * explanation:
     * the second transaction joined the first transaction as a only one transaction
     * the exception thrown by functionWithTransactionrollbackForUserProfileException marked the transaction as roll-back
     * however the transaction thought everything works well since the exception was caught by try-catch
     * and decided to commit.
     * When it was to about to commit, it found the transaction was marked as roll-back which prevented it from committing.
     * In this case, UnexpectedRollbackException was thrown and forced the transaction to roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case28(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            this.userExternalClass.functionWithTransactionrollbackForUserProfileException(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] all users didn't roll back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case29(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            this.userExternalClass.functionWithTransactionPropagationREQUIRES_NEW(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }

    /**
     * [X] user1 didn't roll back. user2 rolled back
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void case30(List<User> users){
        this.userMapper.insert(users.get(0));
        try {
            this.userExternalClass.functionWithTransactionPropagationREQUIRES_NEWrollbackForUserProfileException(users);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
    }


    /**
     * [X]all users didn't roll back
     * @param users
     * @throws UserProfileException
     */
    @Override
    public void case31(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransaction(users);
    }

    /**
     * [X] user1 didn't roll back, user2 rolled back
     * @param users
     * @throws UserProfileException
     */
    @Override
    public void case32(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransactionrollbackForUserProfileException(users);
    }

    /**
     * [X]all users didn't roll back
     * @param users
     * @throws UserProfileException
     */
    @Override
    public void case33(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransactionPropagationREQUIRES_NEW(users);
    }

    /**
     * [X] user1 didn't roll back, user2 rolled back
     * @param users
     * @throws UserProfileException
     */
    @Override
    public void case34(List<User> users) throws UserProfileException {
        this.userMapper.insert(users.get(0));
        this.userExternalClass.functionWithTransactionPropagationREQUIRES_NEWrollbackForUserProfileException(users);
    }
}

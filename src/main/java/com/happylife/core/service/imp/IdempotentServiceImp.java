package com.happylife.core.service.imp;

import com.happylife.core.component.RedisUtils;
import com.happylife.core.component.UUIDGenerator;
import com.happylife.core.exception.IdempotentException;
import com.happylife.core.exception.RedisUtilException;
import com.happylife.core.service.IdempotentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
public class IdempotentServiceImp implements IdempotentService {
    private final static Logger logger = LoggerFactory.getLogger(IdempotentServiceImp.class);
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UUIDGenerator uuidGenerator;

    @Override
    public String createToken() throws IdempotentException {
        String idem_token = uuidGenerator.getUUID().toString().replace("-","");
        try {
            redisUtils.set(idem_token, idem_token,12L, TimeUnit.HOURS);
        } catch (RedisUtilException e) {
            e.printStackTrace();
            throw new IdempotentException(e.getMessage(), e);
        }
        logger.info(this.messageSource.getMessage("idem.token.create.success", new Object[]{idem_token}, Locale.getDefault()));

        return idem_token;
    }

    @Override
    public boolean isExist(String idem_token) throws IdempotentException {
        boolean exist = false;
        try {
            exist = redisUtils.exists(idem_token);
        } catch (RedisUtilException e) {
            e.printStackTrace();
            throw new IdempotentException(e.getMessage(), e);
        }
        return exist;
    }

    @Override
    public void remove(String idem_token) throws IdempotentException {
        try {
            redisUtils.remove(idem_token);
        } catch (RedisUtilException e) {
            e.printStackTrace();
            throw new IdempotentException(e.getMessage(),e);
        }

    }
}

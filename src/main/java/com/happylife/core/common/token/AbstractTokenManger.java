package com.happylife.core.common.token;

import com.happylife.core.component.RedisUtils;
import com.happylife.core.dto.token.Token;
import com.happylife.core.exception.RedisUtilException;
import com.happylife.core.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTokenManger implements TokenManager{
    @Autowired
    private RedisUtils redisUtils;

    @Value("${login.expiry}")
    private long ttl;

    @Autowired
    protected MessageSource messageSource;


    @Override
    public void addToken(Token token) throws TokenException {
        try {
            redisUtils.set(token.getAccess_token(), token, ttl, TimeUnit.SECONDS);
        } catch (RedisUtilException e) {
            throw new TokenException(e.getMessage(), e);
        }

    }

    @Override
    public void deleteToken(String token) throws TokenException {
        try {
            redisUtils.remove(token);
        } catch (RedisUtilException e) {
            throw new TokenException(e.getMessage(), e);
        }
    }

    @Override
    public Token queryToken(String token) throws TokenException {
        Object res = null;
        try {
            res = redisUtils.get(token);
        } catch (RedisUtilException e) {
            throw new TokenException(e.getMessage(), e);
        }
        return (Token) res;
    }

    @Override
    public void updateToken(String token) throws TokenException {
        try {
            redisUtils.reset(token, ttl, TimeUnit.SECONDS);
        } catch (RedisUtilException e) {
            throw new TokenException(e.getMessage(), e);
        }

    }

}

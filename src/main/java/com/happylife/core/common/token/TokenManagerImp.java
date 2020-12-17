package com.happylife.core.common.token;

import com.happylife.core.common.UUIDGenerator;
import com.happylife.core.component.RedisUtils;
import com.happylife.core.exception.RedisUtilException;
import com.happylife.core.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManagerImp implements TokenManager{

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UUIDGenerator uuidGenerator;

    @Value("${login.expiry}")
    private long ttl;

    @Override
    public TokenModel createToken(String name) throws TokenException {
        UUID uuid = uuidGenerator.getUUID();
        String token = uuid.toString().replace("-", "");
        TokenModel tokenModel = null;
        try{
            redisUtils.set(name, token, ttl, TimeUnit.SECONDS);
            tokenModel = new TokenModel(name, token);
        }catch (RedisUtilException e){
            throw new TokenException(e.getMessage(), e);
        }
        return tokenModel;
    }

    @Override
    public void deleteToken(String userId) throws TokenException{
        try{
            redisUtils.remove(userId);
        }catch (RedisUtilException e){
            throw new TokenException(e.getMessage(), e);
        }
    }

    @Override
    public TokenModel queryToken(String authorization) throws TokenException{
        TokenModel tokenModel = null;
        try{
            if(authorization == null || authorization.length() == 0) return tokenModel;
            String[] authorizationParam = authorization.split("-");
            if(authorizationParam.length !=  2) return tokenModel;
            String name = authorizationParam[0];
            String tokenExpected = authorizationParam[1];
            boolean exist = redisUtils.exists(name);
            if(!exist) return tokenModel;
            Object tokenInRedis = redisUtils.get(name);
            if(tokenInRedis == null || !tokenExpected.equals(tokenInRedis.toString()))
                return tokenModel;
            tokenModel = new TokenModel(name, tokenExpected);

        }catch (RedisUtilException e){
            throw new TokenException(e.getMessage(), e);
        }
        return tokenModel;
    }

    @Override
    public void updateTokenTTL(TokenModel tokenModel) throws TokenException{
        try{
            redisUtils.reset(tokenModel.getName(), ttl,TimeUnit.SECONDS);
        }catch (RedisUtilException e){
            throw new TokenException(e.getMessage(), e);
        }
    }
}

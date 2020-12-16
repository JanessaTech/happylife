package com.happylife.core.common.token;

import com.happylife.core.exception.TokenException;
import org.springframework.stereotype.Component;

public interface TokenManager {
    TokenModel createToken(String name) throws TokenException;
    void deleteToken(String name) throws TokenException;
    TokenModel queryToken(String authorization) throws TokenException;
    void updateTokenTTL(TokenModel tokenModel) throws TokenException;
}

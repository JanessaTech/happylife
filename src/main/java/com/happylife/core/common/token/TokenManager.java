package com.happylife.core.common.token;

import com.happylife.core.dto.token.Token;
import com.happylife.core.exception.TokenException;

public interface TokenManager {
    /**
     * generate a token object iif name and password are matched
     * @param name
     * @param password
     * @return
     * @throws TokenException
     */
    Token createToken(String name, String password) throws TokenException;

    /**
     * put a token into the in-memory key-value store(redis)
     * where key is the value of access_token, value is token object
     * @param token
     * @throws TokenException
     */
    void addToken(Token token) throws TokenException;

    /**
     * remove a token from the in-memory store(redis)
     * @param token
     * @throws TokenException
     */
    void deleteToken(String token) throws TokenException;

    /**
     * query whether a token object from the in memory store(redis) exists
     * @param token
     * @return
     * @throws TokenException
     */
    Token queryToken(String token) throws TokenException;

    /**
     * update TTL of the token to the access time
     * @param token
     * @throws TokenException
     */
    Token refreshToken(Token token) throws TokenException;
    void updateToken(String token) throws TokenException;
}

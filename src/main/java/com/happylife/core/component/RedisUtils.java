package com.happylife.core.component;

import com.happylife.core.exception.RedisUtilException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) throws RedisUtilException {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            throw new RedisUtilException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) throws RedisUtilException{
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            throw new RedisUtilException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * reset expiry for a specific key
     * @param key
     * @param expireTime
     * @param timeUnit
     * @throws RedisUtilException
     */
    public void reset(final String key, Long expireTime, TimeUnit timeUnit) throws RedisUtilException{
        try{
            redisTemplate.expire(key, expireTime, timeUnit);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) throws RedisUtilException{
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) throws RedisUtilException {
        if (exists(key)) {
            try{
                redisTemplate.delete(key);
            }catch (Exception e){
                throw new RedisUtilException(e.getMessage(), e);
            }

        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) throws RedisUtilException{
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) throws RedisUtilException{
        Object result = null;
        try{
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }

        return result;
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value) throws RedisUtilException {
        try{
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            hash.put(key, hashKey, value);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(),e);
        }

    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey) throws RedisUtilException {
        try{
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            return hash.get(key, hashKey);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }

    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public void lPush(String k, Object v) throws RedisUtilException{
        try{
            ListOperations<String, Object> list = redisTemplate.opsForList();
            list.rightPush(k, v);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }

    }

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) throws RedisUtilException {
        try{
            ListOperations<String, Object> list = redisTemplate.opsForList();
            return list.range(k, l, l1);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }

    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) throws RedisUtilException {
        try{
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            set.add(key, value);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }

    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) throws RedisUtilException {
        try{
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            return set.members(key);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }

    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) throws RedisUtilException {
        try{
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            zset.add(key, value, scoure);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }

    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) throws RedisUtilException {
        try{
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            return zset.rangeByScore(key, scoure, scoure1);
        }catch (Exception e){
            throw new RedisUtilException(e.getMessage(), e);
        }
    }
}
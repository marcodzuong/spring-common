package com.bachdv.lib.common.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseRedis<T> {

    protected static Logger logger = LoggerFactory.getLogger(BaseRedis.class);
    @Autowired
    protected  RedisTemplate<String,Object> redisTemplate;

    @Autowired
    ObjectMapper mapper;



    public T find(String key, Class<T> aClass) {
        try {
            Object data = redisTemplate.opsForValue().get(key, 0, Integer.MAX_VALUE);
            if (data == null || data.toString().trim().isEmpty()) {
                return null;
            }
            return mapper.readValue(data.toString(), aClass);
        } catch (Exception e) {
            logger.error("Fail to find Item", e);
            return null;
        }
    }

    public List<T> findList(String pattern, Class<T> aClass) {
        List<T> list = new ArrayList<>();
        try {
            Set<String> redisKeys = redisTemplate.keys(pattern);
            if (redisKeys!=null) {
                for (String key : redisKeys) {
                    try {
                        T obj = find(key, aClass);
                        if (obj != null) {
                            list.add(obj);
                        }
                    } catch (Exception ex) {
                        logger.error("Fail to find Item", ex);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            logger.error("Fail to find List Item", e);
            return list;
        }
    }

    public void put(String key, T data) {
        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(data));
        } catch (Exception e) {
            logger.error("Fail to put Item", e);
        }
    }

    public void put(String key, T data, long ttl, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(data), ttl, timeUnit);
        } catch (Exception e) {
            logger.error("Fail to put Item", e);
        }
    }

    public void delete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("Fail to put Item", e);
        }
    }

}

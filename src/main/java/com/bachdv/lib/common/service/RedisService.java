package com.bachdv.lib.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis Operation Service
 *
 * @author BachDV
 * Date : 25/03/2022
 */
public interface RedisService {

    /**
     * save property
     */
    void set(String key, Object value, long time);

    /**
     * save property
     */
    void set(String key, Object value);

    /**
     * get attribute
     */
    Object get(String key);

    /**
     * delete attribute
     */
    Boolean del(String key);

    /**
     * Bulk delete attributes
     */
    Long del(List<String> keys);

    /**
     * Set expiration time
     */
    Boolean expire(String key, long time);

    /**
     * Get expiration time
     */
    Long getExpire(String key);

    /**
     * Determine if there is this attribute
     */
    Boolean hasKey(String key);

    /**
     * Increment by delta
     */
    Long incr(String key, long delta);

    /**
     * Decrement by delta
     */
    Long decr(String key, long delta);

    /**
     * Get the properties in the Hash structure
     */
    Object hGet(String key, String hashKey);

    /**
     * Put a property into the Hash structure
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * Put a property into the Hash structure
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * Get the entire Hash structure directly
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * Directly set the entire Hash structure
     */
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * Directly set the entire Hash structure
     */
    void hSetAll(String key, Map<String, ?> map);

    /**
     * Delete property in Hash structure
     */
    void hDel(String key, Object... hashKey);

    /**
     * Determine whether there is this attribute in the Hash structure
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * Attribute incrementing in Hash structure
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * Attributes decrease in Hash structure
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * Get the Set structure
     */
    Set<Object> sMembers(String key);

    /**
     * Add properties to the Set structure
     */
    Long sAdd(String key, Object... values);

    /**
     * Add properties to the Set structure
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * Is it a property in a Set
     */
    Boolean sIsMember(String key, Object value);

    /**
     * Get the length of the Set structure
     */
    Long sSize(String key);

    /**
     * Delete property in Set structure
     */
    Long sRemove(String key, Object... values);

    /**
     * Get the properties in the List structure
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * Get the length of the List structure
     */
    Long lSize(String key);

    /**
     * Get property in List by index
     */
    Object lIndex(String key, long index);

    /**
     * Add properties to the List structure
     */
    Long lPush(String key, Object value);

    /**
     * Add properties to the List structure
     */
    Long lPush(String key, Object value, long time);

    /**
     * Add properties to the List structure in batches
     */
    Long lPushAll(String key, Object... values);

    /**
     * Add properties to the List structure in batches
     */
    Long lPushAll(String key, Long time, Object... values);

    /**
     * Remove property from List structure
     */
    Long lRemove(String key, long count, Object value);

}

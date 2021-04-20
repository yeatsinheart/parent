package com.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 服务接口
 */
public interface RedisService {
    /**
     * NX: 当且仅当缓存中特定的key不存在时设定数据
     * XX: 当且仅当缓存中特定的key存在时设定数据
     * EX:缓存失效的时间单位：秒
     * XX:缓存失效的时间单位：毫秒
     */
    /**
     * 默认过期时间 ，3600(秒)一个小时
     */
    int DEFAULT_EXPIRE_TIME = 3600;
    ObjectMapper om = new ObjectMapper();
    /**
     * 空白占位符
     */
    String BLANK_CONTENT = "__BLANK__";
    /**
     * 初始化操作
     */
    void init();

    void destroy();


    /**
     * 根据key取数据
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 根据key取对象数据（不支持Collection数据类型）
     *
     * @param key
     * @param clazz
     * @return
     */
    <T> T get(String key, Class<T> clazz);


    /**
     * 根据key取对象数据（不支持Collection数据类型）
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> RedisResult<T> getResult(String key, Class<T> clazz);

    /**
     * 根据key取 Collection 对象数据
     *
     * @param key
     * @param elementClazz 集合元素类型
     * @param <T>
     * @return
     */
    <T> RedisResult<T> getListResult(String key, Class<T> elementClazz);


    /**
     * 写入/修改 缓存内容
     *
     * @param key
     * @param obj
     * @return
     */
    String set(String key, Object obj);


    /**
     * 写入/修改 缓存内容
     *
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);


    /**
     * 写入/修改 缓存内容(无论key是否存在，均会更新key对应的值)
     *
     * @param key
     * @param obj
     * @param expireTime 缓存内容过期时间 （单位：秒） ，若expireTime小于0 则表示该内容不过期
     * @return
     */
    String set(String key, Object obj, int expireTime);


    /**
     * 写入/修改 缓存内容(无论key是否存在，均会更新key对应的值)
     *
     * @param key
     * @param value
     * @param expireTime 缓存内容过期时间 （单位：秒） ，若expireTime小于0 则表示该内容不过期
     * @return
     */
    String set(String key, String value, int expireTime);

    /**
     * 写入/修改 缓存内容
     *
     * @param key
     * @param value
     * @param expiredTime 缓存存活时长，必须 大于0
     * @return
     */
    String set(String key, String value, SetParams setParams, int expiredTime);
    String tryLock(String key, String value, int expiredTime);
    /**
     * 没有key时，才设定缓存内容
     *
     * @param key
     * @param value
     * @param expiredTime 缓存内容过期时间 （单位：秒） ，若expireTime小于0 则表示该内容不过期
     * @return
     */
    String setnx(String key, String value, int expiredTime);

    /**
     * 有key时，才修改缓存内容
     *
     * @param key
     * @param value
     * @param expiredTime 缓存内容过期时间 （单位：秒） ，若expireTime小于0 则表示该内容不过期
     * @return
     */
    String setxx(String key, String value, int expiredTime);


    /**
     * 根据key删除缓存,
     *
     * @param keys
     * @return
     */
    Long delete(String... keys);

    /**
     * 判断对应的key是否存在
     *
     * @param key
     * @return
     */
    boolean exists(String key);


    /**
     * redis 加法运算
     *
     * @param key
     * @param value
     * @return 运算结果
     */
    Long incrBy(String key, long value);

    /**
     * 设定redis 对应的key的剩余存活时间
     *
     * @param key
     * @param seconds
     */
    void setTTL(String key, int seconds);

    /**
     * 根据通配符表达式查询key值的set，通配符仅支持*
     *
     * @param pattern 如 ke6*abc等
     * @return
     */
    Set<String> keys(String pattern);

    void publish(final String channel, final String message);

    /**
     * 订阅给定的一个或多个频道的信息
     *
     * @param jedisPubSub
     * @param channels
     */
    void subscribe(final JedisPubSub jedisPubSub, final String... channels);

    /**
     * 将脚本 script 添加到脚本缓存中，但并不立即执行这个脚本
     *
     * @param script
     * @param <T>
     * @return
     */
    <T> T scriptLoad(final String script);


    /**
     * 对 Lua 脚本进行求值
     *
     * @param sha
     * @param keycount
     * @param args
     * @param <T>
     * @return
     */
    <T> T evalsha(final String sha, final int keycount, final String... args);

    /**
     * 将对象转为json字符串。若对象为null，则返回 {@link RedisService#BLANK_CONTENT}
     *
     * @param object
     * @return
     */
    String toJsonString(Object object);

    /**
     * json序列化对象。
     *
     * @param value
     * @return 返回序列化后的字符串。若value为null，则返回 {@link RedisService#BLANK_CONTENT}
     */
    String makeSerializedString(Object value);

    Long sadd(String key, String members);

    Long lpush(String key, String... members);

    List<String> lrange(String key, Long start, Long end);

    String lpop(String key);

    String ltrim(String key, Long start, Long end);

    boolean sadd(String key, String members, int expireTime);

    Set<String> smembers(String key);

    boolean sismember(String key, String memeber);

    Long srem(String key, String... members);

    String spop(String key);

    Long smove(String srckey, String dstkey, String member);

    Set<String> sunion(String... keys);

    Set<String> sinter(String... keys);

    Set<String> sdiff(String... keys);

    boolean addn(String key, String value, int expiredTime);

    long generateIncr(String key);

    long getAndIncrFromHash(String key, String field, int time, TimeUnit unit);

    Map<String, String> hgetAll(String key);

    void putHashValueWithExpireDate(String key, String field, Object value, int time, TimeUnit unit);

    void putHashValue(String key, String field, Object value);

    /**
     * Hash incr增减操作
     *
     * @param key   键
     * @param field 子
     * @param num   增减数
     * @author Chaims
     * @date 2019/3/16 11:27
     */
    void hashIncr(String key, String field, Integer num);

    /**
     * 没有则新增,有则加
     *
     * @param key   键
     * @param field 子
     * @param num   初始值(增减数)
     * @author Chaims
     */
    void putOrIncrHash(String key, String field, Integer num);

    String getStringFromHash(String key, String field);

    <T> List<T> getHashList(String key, String field, Class<T> clazz);

    /**
     * 根据key获取所有的value
     *
     * @param key ;
     * @return java.util.List<java.lang.String>
     * @author Chaims
     */
    List<String> hvalsHashValues(String key);

    <T> T getHashObject(String key, String field, Class<T> clazz);

    void delHashValue(String key, String field);

    long getKeysLen(String key);

    Boolean tryLock(String key, String value);

    boolean unLock(String key);
}

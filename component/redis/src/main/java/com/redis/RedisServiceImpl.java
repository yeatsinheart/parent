package com.redis;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 服务接口实现类
 */
@Slf4j
@Component("redisService")
public class RedisServiceImpl implements RedisService {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static JedisPool pool = null;
    private static final String KEY_PRE = "REDIS_LOCK_";
    private final String OK_CODE = "OK";
    private final String OK_MULTI_CODE = "+OK";
    private int lockExpirseTime = 2;

    private int tryExpirseTime = 2;


    @Resource
    private Environment env;

    private static <T> JavaType getCollectionType(Class<? extends Collection> collectionClazz, Class<T> elementClazz) {
        return om.getTypeFactory().constructCollectionType(collectionClazz, elementClazz);
    }

    /**
     * 初始化操作
     */
    @Override
    @PostConstruct
    public void init() {
        if (pool != null) {
            return;
        }
        /**
         JedisPoolConfig config = new JedisPoolConfig();
         config.setMaxIdle(dynProps4Files.getInt("REDIS_MAX_IDLE", JedisPoolConfig.DEFAULT_MAX_IDLE));
         config.setMaxTotal(dynProps4Files.getInt("REDIS_MAX_TOTAL", JedisPoolConfig.DEFAULT_MAX_TOTAL));
         config.setMaxWaitMillis(dynProps4Files.getLong("REDIS_MAX_WAIT", JedisPoolConfig.DEFAULT_MAX_WAIT_MILLIS));
         config.setTestOnBorrow(true);
         pool = new JedisPool(config, dynProps4Files.getProperty("REDIS_HOST"),dynProps4Files.getInt("REDIS_PORT", 6379),dynProps4Files.getInt("REDIS_MAX_WAIT", 1000), dynProps4Files.getProperty("REDIS_PASSWORD", null));
         */
        JedisPoolConfig config = new JedisPoolConfig();
        int maxIdle = env.getProperty("spring.redis.pool.max-idle") == null ? JedisPoolConfig.DEFAULT_MAX_IDLE : Integer.valueOf(env.getProperty("spring.redis.pool.max-idle"));
        int maxTotal = env.getProperty("spring.redis.pool.max-active") == null ? JedisPoolConfig.DEFAULT_MAX_TOTAL : Integer.valueOf(env.getProperty("spring.redis.pool.max-active"));
        long maxWaitMillis = env.getProperty("spring.redis.pool.max-wait") == null ? JedisPoolConfig.DEFAULT_MAX_WAIT_MILLIS : Long.valueOf(env.getProperty("spring.redis.pool.max-wait"));
        int port = env.getProperty("spring.redis.port") == null ? 6379 : Integer.valueOf(env.getProperty("spring.redis.port"));

        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(true);
        config.setBlockWhenExhausted(true);

        int timeout = env.getProperty("spring.redis.timeout") == null ? 0 : Integer.valueOf(env.getProperty("spring.redis.timeout"));
        String password = env.getProperty("spring.redis.password");
        if (StringUtils.isEmpty(password)) {
            pool = new JedisPool(config, env.getProperty("spring.redis.host"), port, timeout);
        } else {
            pool = new JedisPool(config, env.getProperty("spring.redis.host"), port, timeout, password);
        }

        om.registerModule(new Jdk8Module());
        JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        om.registerModule(module);

    }

    @Override
    @PreDestroy
    public void destroy() {
        try {
            if (pool != null) {
                pool.destroy();
            }

        } catch (Exception e) {
            //do nothing
        }
    }

    /**
     * 从连接池里取连接（用完连接后必须销毁）
     */
    private Jedis getResource() {
        return pool.getResource();

    }

    /**
     * 用完后，销毁连接（必须）
     */
    private void destroyResource(Jedis jedis) {
        if (jedis == null) {
            return;
        }
        jedis.close();
    }

    /**
     * 根据key取数据
     */
    @Override
    public String get(String key) {

        if (StringUtils.isBlank(key)) {
            log.warn("Params key is blank!");
            return StringUtils.EMPTY;
        }
        Jedis jedis = this.getResource();
        try {
            return jedis.get(key);
        } finally {
            this.destroyResource(jedis);
        }

    }


    /**
     * 根据key取对象数据（不支持Collection数据类型）
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        if (StringUtils.isEmpty(key)) {
            log.warn("Params key is blank!");
            return null;
        }
        if (clazz == null) {
            log.warn("Params clazz is null!");
            return null;
        }
        String value = get(key);
        if (StringUtils.isBlank(value) || StringUtils.equalsIgnoreCase(value, BLANK_CONTENT)) {
            return null;
        }
        T obj = null;
        try {
            obj = om.readValue(value, clazz);
        } catch (IOException e) {
            log.error("Can not unserialize obj to [{}] with string [{}]", clazz.getName(), value);
        }
        return obj;
    }

    /**
     * 根据key取对象数据（不支持Collection数据类型）
     */
    @Override
    public <T> RedisResult<T> getResult(String key, Class<T> clazz) {
        if (StringUtils.isBlank(key)) {
            log.warn("Params key is blank!");
            return null;
        }
        if (clazz == null) {
            log.warn("Params clazz is null!");
            return null;
        }
        RedisResult<T> redisResult = new RedisResult<>();

        String value = get(key);
        if (StringUtils.isBlank(value)) {
            redisResult.setExist(false);
            return redisResult;
        }
        //到此步，则表明redis中存在key
        redisResult.setExist(true);
        if (StringUtils.equalsIgnoreCase(value, BLANK_CONTENT)) {
            return redisResult;
        }
        T obj;
        try {
            obj = om.readValue(value, clazz);
            redisResult.setResult(obj);
        } catch (IOException e) {
            log.error("Can not unserialize obj to [{}] with string [{}]", clazz.getName(), value);
            //到此步直接视为无值
            redisResult.setExist(false);
        }
        return redisResult;
    }

    /**
     * 根据key取 Collection 对象数据
     */
    @Override
    public <T> RedisResult<T> getListResult(String key, Class<T> elementClazz) {
        if (StringUtils.isBlank(key)) {
            log.warn("Params key is blank!");
            return null;
        }

        if (elementClazz == null) {
            log.warn("Params elementClazz is null!");
            return null;
        }
        RedisResult<T> redisResult = new RedisResult<>();

        String value = get(key);
        if (StringUtils.isBlank(value)) {
            redisResult.setExist(false);
            return redisResult;
        }

        //到此步，则表明redis中存在key
        redisResult.setExist(true);
        if (StringUtils.equalsIgnoreCase(value, BLANK_CONTENT)) {
            return redisResult;
        }

        List<T> list;
        try {
            list = om.readValue(value, getCollectionType(List.class, elementClazz));
            redisResult.setListResult(list);
        } catch (IOException e) {
            log.error("getListResult error : {}", e);
            log.error("Can not unserialize list to [{}] with string [{}]", elementClazz.getName(), value);
            //到此步直接视为无值
            redisResult.setExist(false);
        }

        return redisResult;
    }

    /**
     * 写入/修改 缓存内容(无论key是否存在，均会更新key对应的值)
     */
    @Override
    public String set(String key, Object obj, int expireTime) {
        String value = RedisService.BLANK_CONTENT;
        if (obj != null) {
            try {
                value = RedisService.om.writeValueAsString(obj);
            } catch (IOException e) {
                log.error("Can not write object to redis:" + obj.toString(), e);
            }
        }
        return set(key, value, expireTime);
    }

    /**
     * 写入/修改 缓存内容(无论key是否存在，均会更新key对应的值)
     */
    @Override
    public String set(String key, String value, int expireTime) {
        if (StringUtils.isBlank(key)) {
            log.warn("Params key is blank!");
            return null;
        }

        if (value == null) {

            log.warn("Params value is null!");
            return null;
        }

        Jedis jedis = this.getResource();
        try {
            String result = jedis.set(key, value);
            if (expireTime > 0) {
                jedis.expire(key, (long) expireTime);
            }
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 写入/修改 缓存内容
     */
    @Override
    public String set(String key, Object obj) {
        String value = RedisService.BLANK_CONTENT;
        if (obj != null) {
            try {
                value = RedisService.om.writeValueAsString(obj);
            } catch (IOException e) {
                log.error("Can not write object to redis:" + obj.toString(), e);
            }
        }
        return set(key, value);
    }

    /**
     * 写入/修改 缓存内容(默认有过期时间 1小时)
     */
    @Override
    public String set(String key, String value) {
        return this.set(key, value, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 写入/修改 缓存内容
     */
    @Override
    public String set(String key, String value, SetParams setParams, int expiredTime) {
        if (StringUtils.isBlank(key)) {
            log.warn("Params key is blank!");
            return null;
        }

        if (value == null) {
            log.warn("Params value is null!");
            return null;
        }

        Jedis jedis = this.getResource();
        try {
            // set nx ex

            return jedis.set(key, value, setParams);
        } finally {
            this.destroyResource(jedis);
        }

    }

    /**
     * 仅当redis中不含对应的key时，设定缓存内容
     */
    @Override
    public String setnx(String key, String value, int expiredTime) {
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex((long) expiredTime);
        return this.set(key, value, setParams, expiredTime);
    }

    @Override
    public String tryLock(String key, String value, int expiredTime) {
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex((long) expiredTime);
        return this.set(key, value, setParams, expiredTime);
    }


    /**
     * 仅当redis中含有对应的key时，修改缓存内容
     */
    @Override
    public String setxx(String key, String value, int expiredTime) {
        SetParams setParams = new SetParams();
        setParams.xx();
        setParams.ex((long) expiredTime);
        return this.set(key, value, setParams, expiredTime);
    }

    /**
     * 根据key删除缓存
     */
    @Override
    public Long delete(String... keys) {
        if (keys == null || keys.length == 0) {
            log.warn("Params keys is null or 0 length!");
            return -1L;
        }
        Jedis jedis = this.getResource();
        try {
            return jedis.del(keys);
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 判断对应的key是否存在
     */
    @Override
    public boolean exists(String key) {
        if (StringUtils.isBlank(key)) {
            //不接受空值
            return false;
        }
        Jedis jedis = this.getResource();
        try {
            return jedis.exists(key);
        } finally {
            this.destroyResource(jedis);
        }


    }

    /**
     * 添加风控有关的redis操作接口
     */

    /**
     * redis 加法运算
     */
    @Override
    public Long incrBy(String key, long value) {
        if (StringUtils.isBlank(key)) {
            log.warn("Params key is blank!");
            return null;
        }
        Jedis jedis = this.getResource();
        try {
            return jedis.incrBy(key, value);
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 设定redis 对应的key的剩余存活时间
     */
    @Override
    public void setTTL(String key, int seconds) {
        if (seconds < 0) {
            return;
        }
        if (StringUtils.isBlank(key)) {
            log.warn("Params key is blank!");
            return;
        }
        Jedis jedis = this.getResource();
        try {
            jedis.expire(key, (long) seconds);
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 发布消息到指定的频道
     */
    @Override
    public void publish(final String channel, final String message) {
        Jedis jedis = this.getResource();
        jedis.publish(channel, message);
        this.destroyResource(jedis);
    }

    /**
     * 订阅给定的一个或多个频道的信息
     */
    @Override
    public void subscribe(final JedisPubSub jedisPubSub, final String... channels) {
        Jedis jedis = this.getResource();
        jedis.subscribe(jedisPubSub, channels);
        this.destroyResource(jedis);
    }

    /*************************************风控操作接口END*************************************/

    /**
     * 将脚本 script 添加到脚本缓存中，但并不立即执行这个脚本
     */
    @Override
    public <T> T scriptLoad(final String script) {
        Jedis jedis = this.getResource();
        T obj;
        try {
            obj = (T) jedis.scriptLoad(script);
            return obj;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /***************************************************jedis操作set***************************************************/

    /**
     * 对 Lua 脚本进行求值
     */
    @Override
    public <T> T evalsha(final String sha, final int keycount, final String... args) {
        Jedis jedis = this.getResource();
        T obj;
        try {
            obj = (T) jedis.evalsha(sha, keycount, args);
            return obj;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 根据通配符表达式查询key值的set，通配符仅支持*
     */
    @Override
    public Set<String> keys(String pattern) {

        if (StringUtils.isBlank(pattern)) {
            log.warn("Params pattern is blank!");
            return Collections.emptySet();
        }
        Jedis jedis = this.getResource();
        try {
            return jedis.keys(pattern);
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 把一个或多个元素添加到指定集合
     */
    @Override
    public Long sadd(String key, String members) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(members)) {
            log.warn("key or member is blank");
            return null;
        }
        Jedis jedis = this.getResource();
        Long result;
        try {
            result = jedis.sadd(key, members);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 插入集合
     */
    @Override
    public Long lpush(String key, String... members) {
        if (StringUtils.isBlank(key) || members == null || members.length == 0) {
            log.warn("key or member is blank");
            return null;
        }
        Jedis jedis = this.getResource();
        Long result;
        try {
            result = jedis.lpush(key, members);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 获取队列
     */
    @Override
    public List<String> lrange(String key, Long start, Long end) {
        if (StringUtils.isBlank(key) || end == null || start == null) {
            log.warn("key or index is blank");
            return null;
        }
        Jedis jedis = this.getResource();
        List<String> result;
        try {
            result = jedis.lrange(key, start, end);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 获取并删除集合中的第一个值
     */
    @Override
    public String lpop(String key) {
        if (StringUtils.isBlank(key)) {
            log.warn("key is blank");
            return null;
        }
        Jedis jedis = this.getResource();
        String result;
        try {
            result = jedis.lpop(key);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 保留指定区间元素,成功返回ok
     */
    @Override
    public String ltrim(String key, Long start, Long end) {
        if (StringUtils.isBlank(key)) {
            log.warn("key is blank");
            return null;
        }
        Jedis jedis = this.getResource();
        String result;
        try {
            result = jedis.ltrim(key, start, end);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 添加元素到指定集合并设置集合的有效期
     */
    @Override
    public boolean sadd(String key, String members, int expireTime) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(members)) {
            log.warn("key or member is blank");
            return false;
        }
        Jedis jedis = this.getResource();
        List<Object> result;
        try {
            // 开始事务
            Transaction transaction = jedis.multi();
            transaction.sadd(key, members);
            transaction.expire(key, (long) expireTime);
            // 执行事务 result中返回两个Long类型的1
            result = transaction.exec();
            for (Object rt : result) {
                if (!isStatusOk(rt.toString())) {
                    return false;
                }
            }
            return true;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 返回集合所有成员
     */
    @Override
    public Set<String> smembers(String key) {
        if (StringUtils.isBlank(key)) {
            log.warn("key is blank");
            return null;
        }
        Jedis jedis = this.getResource();
        Set<String> result;
        try {
            result = jedis.smembers(key);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 判断元素是否是集合成员
     */

    @Override
    public boolean sismember(String key, String memeber) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(memeber)) {
            log.warn("key or member is blank");
            return false;
        }

        Jedis jedis = this.getResource();
        boolean result;
        try {
            result = jedis.sismember(key, memeber);

            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 移除一个或多个元素，不存的元素会被忽略
     */
    @Override
    public Long srem(String key, String... members) {
        if (StringUtils.isBlank(key)) {
            log.warn("key is blank");
            return null;
        }

        Jedis jedis = this.getResource();
        Long result;
        try {
            result = jedis.srem(key, members);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 随机移除并返回一个元素
     */
    @Override
    public String spop(String key) {
        if (StringUtils.isBlank(key)) {
            log.warn("key is blank");
            return null;
        }

        Jedis jedis = this.getResource();
        String result;
        try {
            result = jedis.spop(key);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 把指定成员从一个集合移动到目标集合，指定成员不存在，不执行任何操作
     */
    @Override
    public Long smove(String srckey, String dstkey, String member) {
        if (StringUtils.isBlank(srckey) || StringUtils.isBlank(dstkey) || StringUtils.isBlank(member)) {
            log.warn("srckey or dstkey or member is blank");
            return null;
        }

        Jedis jedis = this.getResource();
        Long result;
        try {
            result = jedis.smove(srckey, dstkey, member);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 返回指定集合的并集
     */
    @Override
    public Set<String> sunion(String... keys) {
        Jedis jedis = this.getResource();
        Set<String> result;

        try {
            result = jedis.sunion(keys);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 返回多个集合的交集
     */
    @Override
    public Set<String> sinter(String... keys) {
        Jedis jedis = this.getResource();

        Set<String> result;
        try {
            result = jedis.sinter(keys);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 返回多个集合的差集
     */
    @Override
    public Set<String> sdiff(String... keys) {
        Jedis jedis = this.getResource();
        Set<String> result;
        try {
            result = jedis.sdiff(keys);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 当key不存在时放入值，超时时间单位为秒
     */
    @Override
    public boolean addn(String key, String value, int expiredTime) {
        if (StringUtils.isBlank(key)) {
            log.warn("key is blank");
            return false;
        }

        Jedis jedis = this.getResource();
        String result;
        try {
            SetParams setParams = new SetParams();
            setParams.nx();
            setParams.ex(expiredTime <= 0 ? (long) 1 : (long) expiredTime);
            result = jedis.set(key, value, setParams);
            return isStatusOk(result);
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 判断 返回值是否ok.
     */
    public boolean isStatusOk(String status) {
        return (OK_CODE.equals(status) || OK_MULTI_CODE.equals(status) || "1".equals(status));
    }

    /***************************************************jedis操作set End***************************************************/

    /**
     * 将对象转为json字符串。若对象为null，则返回 {@link RedisService#BLANK_CONTENT}
     */
    @Override
    public String toJsonString(Object object) {
        if (object == null) {
            return BLANK_CONTENT;
        }

        if ((object instanceof Collection) && CollectionUtils.isEmpty((Collection) object)) {
            return BLANK_CONTENT;
        }

        if ((object instanceof Map) && CollectionUtils.isEmpty((Map) object)) {
            return BLANK_CONTENT;
        }

        try {
            return om.writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String makeSerializedString(Object value) {
        if (value == null) {
            return BLANK_CONTENT;
        }

        if ((value instanceof Collection) && ((Collection) value).size() == 0) {
            return BLANK_CONTENT;
        }

        if ((value instanceof Map) && ((Map) value).size() == 0) {
            return BLANK_CONTENT;
        }


        return JSON.toJSONString(value);
    }

    /**
     * Atomically increments by one the current value.
     */
    @Override
    public long generateIncr(String key) {
        Jedis jedis = this.getResource();
        long result;
        try {
            result = jedis.incrBy(key, 1);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 自增map
     */
    @Override
    public long getAndIncrFromHash(String key, String field, int time, TimeUnit unit) {
        Jedis jedis = this.getResource();
        long result = 0;
        try {
            if (jedis.hexists(key, field)) {
                return jedis.hincrBy(key, field, 1);
            }
            jedis.hset(key, field, "1");
            jedis.expire(key, (long) time);
            return result;
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 插入HASH值，并设置整个HASH过期时间
     */
    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.hgetAll(key);
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public void putHashValueWithExpireDate(String key, String field, Object value, int time, TimeUnit unit) {
        Jedis jedis = this.getResource();
        try {
            jedis.hset(key, field, JSON.toJSONString(value));
            jedis.expire(key, (long) time);
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public void putHashValue(String key, String field, Object value) {
        Jedis jedis = this.getResource();
        try {
            jedis.hset(key, field, JSON.toJSONString(value));
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public void hashIncr(String key, String field, Integer num) {
        Jedis jedis = this.getResource();
        try {
            if (jedis.hexists(key, field)) {
                jedis.hincrBy(key, field, num);
            }

        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public void putOrIncrHash(String key, String field, Integer num) {
        Jedis jedis = this.getResource();
        try {
            if (jedis.hexists(key, field)) {
                jedis.hincrBy(key, field, num);
            } else {
                jedis.hset(key, field, JSON.toJSONString(num));
            }
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public <T> T getHashObject(String key, String field, Class<T> clazz) {
        Jedis jedis = this.getResource();
        try {
            if (!jedis.hexists(key, field)) {
                return null;
            }
            String json = jedis.hget(key, field);
            return json == null ? null : JSON.parseObject(json, clazz);
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public <T> List<T> getHashList(String key, String field, Class<T> clazz) {
        Jedis jedis = this.getResource();
        try {
            if (!jedis.hexists(key, field)) {
                return null;
            }
            String json = jedis.hget(key, field);
            return json == null ? null : JSON.parseArray(json, clazz);
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public List<String> hvalsHashValues(String key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.hvals(key);
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public String getStringFromHash(String key, String field) {
        Jedis jedis = this.getResource();
        try {
            if (!jedis.hexists(key, field)) {
                return null;
            }
            String json = jedis.hget(key, field);
            return json == null ? null : JSON.parseObject(json, String.class);
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 删除HASH值
     */
    @Override
    public void delHashValue(String key, String field) {
        Jedis jedis = this.getResource();
        try {
            jedis.hdel(key, field);
        } finally {
            this.destroyResource(jedis);
        }
    }

    /**
     * 获取在线用户数 TODO 数据量大要测试性能问题
     */
    @Override
    public long getKeysLen(String key) {
        Jedis jedis = this.getResource();
        try {
            Set<String> set = jedis.keys(key);
            return set.size();
        } finally {
            this.destroyResource(jedis);
        }
    }

    @Override
    public Boolean tryLock(String key, String value) {
        try {
            key = KEY_PRE + key;
            Long firstTryTime = new Date().getTime();
            do {
                if (LOCK_SUCCESS.equals(tryLock(key, value, lockExpirseTime))) {
                    return true;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.info("try lock InterruptedException {}", e);
                }
            } while ((new Date().getTime() - tryExpirseTime * 1000) < firstTryTime);
            //两秒内重试获取锁哦
        } catch (Exception e) {
            log.info("try lock Exception {}", e);
        }
        return false;
    }

    @Override
    public boolean unLock(String key) {
        try {
            delete(KEY_PRE + key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

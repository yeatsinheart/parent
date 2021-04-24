package com.db.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
//IdType.ASSIGN_ID
@Component
public class GlobalIdGenerator implements IdentifierGenerator {
    static ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
    static Lock readLock = reentrantLock.readLock();
    static Lock writeLock = reentrantLock.writeLock();
    ConcurrentHashMap<String, AtomicLong> nowIdMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, AtomicLong> counterMap = new ConcurrentHashMap<>();

    @Override
    public Long nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        //本地未初始化或者已经使用了200个。就向数据库更新一次，不做异步防止问题。
        AtomicLong counter = counterMap.get(bizKey);
        AtomicLong nowId = nowIdMap.get(bizKey);
        if (null == counter || counter.get() == 200) {
            updateCacheId(bizKey, counter, nowId);
        }
        counter.getAndAdd(1);
        long id = nowId.addAndGet(1);
        return id;
    }

    public void updateCacheId(String bizKey, AtomicLong counter, AtomicLong nowId) {
        writeLock.lock();
        try {
            // 保证只有一个线程进入，进入后重复判断是否已经被修改，防止并发数据库重置当前ID时有问题
            if (null == counter || counter.get() == 200) {
                //根据bizKey调用分布式ID生成
                // 数据库中存在global_id_generate表
                // 事务内 查询当前值（注意不能脏读）nowId，数据库表ID+200，保存
                // counter 重置为0
                nowId = new AtomicLong(0);
                counter = new AtomicLong(0);
            }
        } finally {
            writeLock.unlock();
        }
    }
}

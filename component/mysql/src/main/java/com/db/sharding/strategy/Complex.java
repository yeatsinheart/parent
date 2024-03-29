package com.db.sharding.strategy;

import com.base.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;

@Slf4j
public class Complex implements ComplexKeysShardingAlgorithm<Long> {
    // 分表策略 时间分片_用户ID_自己分片
    // 分片因素太多。则影响到具体的跨分片的查询
    // 是否时间报表，决定表是否走时间分片
    // 基本都是用户关联数据，所以使用用户ID作为分片
    // 时间一律使用时间戳
    // 租户——节点：用户
    private static final String defaultTable = "user_3";

    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> shardingValue) {
        Collection<String> target = new HashSet<>();
        log.warn(shardingValue.getLogicTableName() + "=>" + JsonUtil.toJsonStr(collection));
        // =, IN 查询
        log.warn(JsonUtil.toJsonStr(shardingValue.getColumnNameAndShardingValuesMap()));
        // 根据自身ID 决定在什么表
        Collection<Long> idParams = shardingValue.getColumnNameAndShardingValuesMap().get("id");
        // 根据租户决定在什么表
        Collection<Long> tenantIdParams = shardingValue.getColumnNameAndShardingValuesMap().get("tenant_id");
        if (CollectionUtils.isEmpty(idParams)) {
            target.add(defaultTable);
        }
        target.add(defaultTable);
        // 范围查询BETWEEN AND的分片操作支持
        // 没有时间范围，默认走当天的时间分片
        log.warn(JsonUtil.toJsonStr(shardingValue.getColumnNameAndRangeValuesMap()));
        /*Collection<Long> idValues = (Collection<Long>) complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("id");
            Range<Long> timeValues = (Range<Long>) complexKeysShardingValue.getColumnNameAndRangeValuesMap().get("time");
        */

        return target;

    }
}

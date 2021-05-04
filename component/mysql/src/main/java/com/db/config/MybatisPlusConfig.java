package com.db.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//mybatis-plus.mapper-locations.classpath*=mapper/*.xml
@Configuration
@MapperScan("db.*.mappers")
public class MybatisPlusConfig {
    @Autowired
    private TenantContext tenantContext;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        /*注意:

        使用多个功能需要注意顺序关系,建议使用如下顺序
        多租户,动态表名,分页,乐观锁
        sql性能规范,防止全表更新与删除
        总结: 对sql进行单次改造的优先放入,不对sql进行改造的最后放入*/
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 租户改写 由业务保证，有设定
        //tenantLineInnerInterceptor(interceptor);
        // 动态表名 根据业务场景不同确定调用什么表？
        dynamicTableNameInnerInterceptor(interceptor);
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

    //日志或者其他数据量大的表，通过日期进行了水平分表，需要通过日期参数，动态的查询数据。
    public void dynamicTableNameInnerInterceptor(MybatisPlusInterceptor interceptor) {
        // 动态表名插件
        DynamicTableNameInnerInterceptor dynamicTabNameInterceptor = new DynamicTableNameInnerInterceptor();
        Map<String, TableNameHandler> tableNameHandlerMap = new HashMap<>();
        //具体表的 分表策略
        tableNameHandlerMap.put("log", new TableNameHandler() {
            @Override
            public String dynamicTableName(String sql, String tableName) {
                //String[] tableSuffix = {"2018", "2019", "2020"};
                //+ "_" + tableSuffix[(int) (Math.random() * tableSuffix.length)]
                return tableName;
            }
        });
        dynamicTabNameInterceptor.setTableNameHandlerMap(tableNameHandlerMap);
        interceptor.addInnerInterceptor(dynamicTabNameInterceptor);
    }

    // 多租户方案
    public void tenantLineInnerInterceptor(MybatisPlusInterceptor interceptor) {
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
        tenantLineInnerInterceptor.setTenantLineHandler(new TenantLineHandler() {
            List<String> tables = Arrays.asList("global_api", "global_resource");

            @Override
            public Expression getTenantId() {
                Long tenant = tenantContext.getTenantId();
                if (tenant != null) {
                    return new LongValue(tenantContext.getTenantId());
                }
                return new NullValue();
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean ignoreTable(String tableName) {
                return tables.contains(tableName);
            }
        });
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
    }

}

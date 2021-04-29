package com.db.config;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseDao<M extends BaseMapper<T>, T> implements IService<T> {
    protected Log log = LogFactory.getLog(this.getClass());
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected M baseMapper;
    protected Class<T> entityClass = this.currentModelClass();
    protected Class<T> mapperClass = this.currentMapperClass();

    public BaseDao() {
    }

    public M getBaseMapper() {
        return this.baseMapper;
    }

    public Class<T> getEntityClass() {
        return this.entityClass;
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentMapperClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), 0);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(this.entityClass);
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(this.entityClass));
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(this.entityClass).getSqlStatement(sqlMethod.getMethod());
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            sqlSession.insert(sqlStatement, entity);
        });
    }

    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(this.mapperClass, sqlMethod);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean saveOrUpdate(T entity) {
        if (null == entity) {
            return false;
        } else {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
            Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            return !StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable) idVal)) ? this.updateById(entity) : this.save(entity);
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = ReflectionKit.getFieldValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal) || CollectionUtils.isEmpty(sqlSession.selectList(this.getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
            param.put("et", entity);
            sqlSession.update(this.getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
            param.put("et", entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return throwEx ? this.baseMapper.selectOne(queryWrapper) : SqlHelper.getObject(this.log, this.baseMapper.selectList(queryWrapper));
    }

    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return (Map) SqlHelper.getObject(this.log, this.baseMapper.selectMaps(queryWrapper));
    }

    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return SqlHelper.getObject(this.log, this.listObjs(queryWrapper, mapper));
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected boolean executeBatch(Consumer<SqlSession> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, consumer);
    }

    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

    protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return this.executeBatch(list, 1000, consumer);
    }


    /**
     * 根据条件删除
     **/
    public boolean remove(T query) {
        return SqlHelper.retBool(this.getBaseMapper().delete(new QueryWrapper(query)));
    }
    /**
     * 根据条件修改
     **/
    public boolean update(T entity,T query) {
        return SqlHelper.retBool(this.getBaseMapper().update(entity, new QueryWrapper(query)));
    }

    /**
     * 根据条件查唯一的一个
     **/
    public T getOne(T query) {
        T entity = (T) this.getBaseMapper().selectOne(new QueryWrapper(query));
        return entity;
    }
    /**
     * 根据条件修改
     **/
    public int count(T query) {
        return SqlHelper.retCount(this.getBaseMapper().selectCount(new QueryWrapper(query)));
    }

    /**
     * 根据条件查找列表
     **/
    public List<T> list(T query) {
        List<T> entities = this.getBaseMapper().selectList(new QueryWrapper(query));
        return entities;
    }

    /**
     * 分页查找数据
     **/
    public Page<T> page(T query, long pageSize, long pageNum) {
        Page<T> page = new Page(pageNum, pageSize);
        page = getBaseMapper().selectPage(page, new QueryWrapper(query));
        return page;
    }

    /**
     * 根据条件修改数据
     **/
    public int updateByQuery(T updateEntity, T query) {
        int updatedNum = getBaseMapper().update(updateEntity,  new QueryWrapper(query));
        return updatedNum;
    }
    public boolean saveOrUpdate(T entity, T query) {
        return this.update(entity, new QueryWrapper(query)) || this.saveOrUpdate(entity);
    }
    protected abstract Wrapper<T> getQueryWrapperNotNull(T query);

}

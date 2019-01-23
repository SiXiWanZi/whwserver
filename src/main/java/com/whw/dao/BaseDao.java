package com.whw.dao;

import com.whw.util.Page;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <T>
 */
public interface BaseDao<T> {

    /**
     * 保存一个对象
     * @param o 对象
     * @return 主键
     */
    Serializable save(T o);

    /**
     * 删除一个对象
     * @param o 对象
     */
    void delete(T o);

    /**
     * 更新一个对象
     * @param o 对象
     */
    void update(T o);

    /**
     * 保存或更新对象
     * @param o 对象
     */
    void saveOrUpdate(T o);

    /**
     * 查询
     * @param hql   hql语句
     * @return
     */
    List<T> find(String hql);

    /**
     *
     * @param hql   hql语句
     * @param page
     * @return
     */
    List<T> find(String hql, Page page);

    /**
     * 查询集合
     * @param hql   hql语句
     * @param params 参数
     * @return
     */
    List<T> find(String hql, Object... params);

    /**
     *
     * @param hql  hql语句
     * @param params 参数
     * @param page
     * @return
     */
    List<T> find(String hql, Page page, Object... params);

    /**
     * 查询集合
     * @param hql   hql语句
     * @param params 参数
     * @return
     */
    List<T> find(String hql, List<Object> params);

    /**
     *
     * @param hql hql语句
     * @param params 参数
     * @param page
     * @return
     */
    List<T> find(String hql, List<Object> params, Page page);

    /**
     * 查询集合(带分页)
     *
     * @param hql   hql语句
     * @param params 参数
     * @param page  查询第几页
     * @param rows  每页显示几条记录
     * @return
     */
    List<T> find(String hql, Integer page, Integer rows, Object... params);

    /**
     * 查询集合(带分页)
     * @param hql   hql语句
     * @param params    参数
     * @param page 当前页数
     * @param rows 查询行数
     * @return
     */
    List<T> find(String hql, List<Object> params, Integer page, Integer rows);

    /**
     * 获得一个对象
     * @param c 对象类型
     * @param id 主键Id
     * @return Object
     */
    T get(Class<T> c, Serializable id);

    /**
     * 获得一个对象
     * @param hql   hql语句
     * @param params 参数
     * @return Object
     */
    T get(String hql, Object... params);

    /**
     * 获得一个对象
     * @param hql   hql语句
     * @param params 参数
     * @return
     */
    T get(String hql, List<Object> params);

    /**
     * select count(*) from 类
     * @param hql   hql语句
     * @return
     */
    Long count(String hql);

    /**
     * select count(*) from 类
     * @param hql   hql语句
     * @param params 参数
     * @return
     */
    Long count(String hql, Object... params);

    /**
     * select count(*) from 类
     * @param hql   hql语句
     * @param params 参数
     * @returns
     */
    Long count(String hql, List<Object> params);

    /**
     * 执行HQL语句
     * @param hql   hql语句
     * @return 响应数目
     */
    Integer executeHql(String hql);

    /**
     * 执行HQL语句
     * @param hql   hql语句
     * @param params 参数
     * @return 响应数目
     */
    Integer executeHql(String hql, Object... params);

    /**
     * 执行HQL语句
     * @param hql   hql语句
     * @param params    参数
     * @return 响应数目
     */
    Integer executeHql(String hql, List<Object> params);

}


package com.whw.dao.impl;

import com.whw.dao.BaseDao;
import com.whw.util.CastUtil;
import com.whw.util.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luolei
 * Date: 2016/1/6
 * Time: 19:19
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
        //
    }

    @Override
    public Serializable save(T o) {
        return getCurrentSession().save(o);
    }

    @Override
    public void delete(T o) {
        getCurrentSession().delete(o);
    }

    @Override
    public void update(T o) {
        getCurrentSession().update(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        getCurrentSession().saveOrUpdate(o);
    }

    /**
     * 执行HQL语句，返回List接口，获取查询结果
     * @param hql   hql语句
     * @return
     */
    @Override
    public List<T> find(String hql) {
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<T> find(String hql, Object...params) {
        Query query = getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.list();
    }

    @Override
    public List<T> find(String hql, List<Object> params) {
        Query query = this.getCurrentSession().createQuery(hql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                query.setParameter(i, params.get(i));
            }
        }
        return query.list();
    }



    @Override
    public List<T> find(String hql, Page page) {
        String countHql = "select count(*) " + hql;
        Session session = this.getCurrentSession();
        Query countQuery = session.createQuery(countHql);
        Query query = session.createQuery(hql);
        long totalRecord = (long)countQuery.uniqueResult();
        page.count(CastUtil.castInt(totalRecord));
//        int totalRecord = query.list().size();
//        page.count(totalRecord);
        int start = page.getQueryIndex();
        int length = page.getQueryLength();
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

    @Override
    public List<T> find(String hql, Page page, Object...params) {
        String countHql = "select count(*) " + hql;
        Session session = this.getCurrentSession();
        Query countQuery = session.createQuery(countHql);
        Query query = session.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
                countQuery.setParameter(i, params[i]);
            }
        }
        long totalRecord = (long)countQuery.uniqueResult();
        page.count(CastUtil.castInt(totalRecord));
//        int totalRecord = query.list().size();
//        page.count(totalRecord);
        int start = page.getQueryIndex();
        int length = page.getQueryLength();
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

    @Override
    public List<T> find(String hql, List<Object> params, Page page) {
        String countHql = "select count(*) " + hql;
        Session session = this.getCurrentSession();
        Query countQuery = session.createQuery(countHql);
        Query query = session.createQuery(hql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                query.setParameter(i, params.get(i));
                countQuery.setParameter(i, params.get(i));
            }
        }
        long totalRecord = (long)countQuery.uniqueResult();
        page.count(CastUtil.castInt(totalRecord));
//        int totalRecord = query.list().size();
//        page.count(totalRecord);
        int start = page.getQueryIndex();
        int length = page.getQueryLength();
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

    @Override
    public List<T> find(String hql, Integer page, Integer rows, Object...params) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i, params[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List<T> find(String hql, List<Object> params, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i, params.get(i));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }


    /**
     * 根据主键获取对象(需要序列化的主键，故不常用)
     * @param c 对象类型
     * @param id 主键Id
     * @return
     */
    @Override
    public T get(Class<T> c, Serializable id) {
        return  this.getCurrentSession().get(c, id);
    }

    /**
     * 根据HQL语句进行查询，获取满足条件的第一个对象（较常用）
     * @param hql   hql语句
     * @param params 参数
     * @return
     */
    @Override
    public T get(String hql, Object...params) {
        List<T> l = this.find(hql, params);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public T get(String hql, List<Object> params) {
        List<T> l = this.find(hql, params);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    /**
     * 传入查询记录数的HQL语句
     * @param hql   hql语句
     * @return
     */
    @Override
    public Long count(String hql) {
        return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
    }

    @Override
    public Long count(String hql, Object...params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i, params[i]);
            }
        }
        return (Long) q.uniqueResult();
    }

    @Override
    public Long count(String hql, List<Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i, params.get(i));
            }
        }
        return (Long) q.uniqueResult();
    }


    /**
     * 执行增删改、及DDL语句，返回受影响行数
     * @param hql   hql语句
     * @return
     */
    @Override
    public Integer executeHql(String hql) {
        return this.getCurrentSession().createQuery(hql).executeUpdate();
    }

    @Override
    public Integer executeHql(String hql, Object...params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i, params[i]);
            }
        }
        return q.executeUpdate();
    }

    @Override
    public Integer executeHql(String hql, List<Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i, params.get(i));
            }
        }
        return q.executeUpdate();
    }
}

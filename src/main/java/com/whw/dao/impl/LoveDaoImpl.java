package com.whw.dao.impl;


import com.whw.dao.LoveDao;
import com.whw.model.Love;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class LoveDaoImpl extends BaseDaoImpl<Love> implements LoveDao {


    @Override
    public int countLoveByWpbh(String wpbh) {
        String hql = "from Love l where l.wpbh = ?";
        return (int) (long) count(hql, wpbh);
    }

    @Override
    public int countLoveByJsbh(String jsbh) {
        String hql = "from Love l where l.jsbh = ?";
        return (int) (long) count(hql, jsbh);
    }

    @Override
    public List<Love> findByWpbh(String wpbh) {
        String hql = "from Love l where l.wpbh = ? order by l.dzsj";
        return find(hql, wpbh);
    }

    @Override
    public List<Love> findByJsbh(String jsbh) {
        String hql = "from Love l where l.jsbh = ? order by l.dzsj";
        return find(hql, jsbh);
    }

    @Override
    public void cancelLoveWp(String user, String wpbh) {
        String hql = "from Love l where l.yhbh=? and l.wpbh = ?";
        Love love = get(hql, user, wpbh);
        if (love != null) {
            delete(love);
        }
    }

    @Override
    public void cancelLoveJs(String user, String jsbh) {
        String hql = "from Love l where l.yhbh=? and l.jsbh = ?";
        Love love = get(hql, user, jsbh);
        if (love != null) {
            delete(love);
        }
    }
}

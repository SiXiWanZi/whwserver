package com.whw.dao.impl;


import com.whw.dao.StarDao;
import com.whw.model.Star;
import com.whw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class StarDaoImpl extends BaseDaoImpl<Star> implements StarDao {

    @Override
    public List<Star> findByYhbhAndWpbh(String yhbh, String wpbh) {
        String hql = "from Star l where l.yhbh=? and l.wpbh = ? order by l.scsj";
        return find(hql, yhbh, wpbh);
    }

    @Override
    public List<Star> findByUserAndWpbh(User user, String wpbh) {
        return findByYhbhAndWpbh(user.getYhbh(), wpbh);
    }

    @Override
    public List<Star> findByYhbhAndJsbh(String user, String jsbh) {
        String hql = "from Star l where l.yhbh=? and l.jsbh = ? order by l.scsj";
        return find(hql, user, jsbh);
    }

    @Override
    public List<Star> findByUserAndJsbh(User user, String jsbh) {
        return findByYhbhAndJsbh(user.getYhbh(), jsbh);
    }

    @Override
    public void cancelStarWp(String user, String wpbh) {
        String hql = "from Star s where s.yhbh=? and s.wpbh = ?";
        Star star = get(hql, user, wpbh);
        if (star != null) {
            delete(star);
        }
    }

    @Override
    public void cancelStarJs(String user, String jsbh) {
        String hql = "from Star s where s.yhbh=? and s.jsbh = ?";
        Star star = get(hql, user, jsbh);
        if (star != null) {
            delete(star);
        }
    }
}

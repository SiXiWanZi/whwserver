package com.whw.dao.impl;


import com.whw.dao.FeedBackDao;
import com.whw.model.FeedBack;
import com.whw.model.User;
import com.whw.util.Page;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class FeedBackDaoImpl extends BaseDaoImpl<FeedBack> implements FeedBackDao {

    @Override
    public List<FeedBack> findByUser(User user) {
        String hql = "from FeedBack fb where fb.yhbh = ? order by fb.fbsj";
        return find(hql, user.getYhbh());
    }

    @Override
    public List<FeedBack> findAll() {
        String hql = "from FeedBack fb order by fb.fbsj";
        return find(hql);
    }

    @Override
    public List<FeedBack> findAll(Page page) {
        String hql = "from FeedBack fb order by fb.fbsj";
        return find(hql, page);
    }

    @Override
    public List<FeedBack> findByDay(Date ldt) {
        Date ldt1 = new Date(ldt.getTime()+24L*60*60*1000);

        String hql = "from FeedBack fb where fb.fbsj>= ?and fb.fbsj<= ? order by fb.fbsj";
        return find(hql, ldt, ldt1);
    }
}

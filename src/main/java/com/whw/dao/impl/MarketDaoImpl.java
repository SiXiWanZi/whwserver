package com.whw.dao.impl;


import com.whw.dao.MarketDao;
import com.whw.model.Market;
import com.whw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class MarketDaoImpl extends BaseDaoImpl<Market> implements MarketDao {


    @Override
    public List<Market> findAll() {
        return find("from Market m order by m.ftsj desc");
    }

    @Override
    public List<Market> findByZT(String zt) {
        return find("from Market m where m.zt like ? order by m.ftsj desc", "%" + zt + "%");
    }

    @Override
    public List<Market> findByUser(User user) {
        return findByUser(user.getYhbh());
    }

    @Override
    public List<Market> findByUser(String user) {
        return find("from Market m where m.yhbh = ? order by m.ftsj desc", user);
    }
}

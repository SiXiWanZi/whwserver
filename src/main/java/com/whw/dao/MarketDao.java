package com.whw.dao;


import com.whw.model.Market;
import com.whw.model.User;
import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface MarketDao extends BaseDao<Market> {

    List<Market> findAll();

    List<Market>findByZT(String zt);

    List<Market> findByUser(User user);

    List<Market> findByUser(String user);


}

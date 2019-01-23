package com.whw.dao;


import com.whw.model.FeedBack;
import com.whw.model.User;
import com.whw.util.Page;
import java.util.Date;
import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface FeedBackDao extends BaseDao<FeedBack> {
    List<FeedBack> findByUser(User user);
    List<FeedBack> findAll();
    List<FeedBack> findAll(Page page);

    List<FeedBack> findByDay(Date ldt);
}

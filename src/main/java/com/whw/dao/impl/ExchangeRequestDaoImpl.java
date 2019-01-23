package com.whw.dao.impl;


import com.whw.dao.ExchangeRequestDao;
import com.whw.model.ExchangeRequest;
import com.whw.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class ExchangeRequestDaoImpl extends BaseDaoImpl<ExchangeRequest> implements ExchangeRequestDao {

    /**
     * tc start
     */

    /**
     * 根据用户编号和page查询换物请求
     *
     * @param yhbh
     * @param page
     * @return
     */
    @Override
    public List<ExchangeRequest> findByUser(String yhbh, Page page) {
        String hql = "from ExchangeRequest er where er.yhbh = ? order by er.xzsj desc";
        return find(hql, page, yhbh);
    }

    /**
     * tc end
     */
}

package com.whw.dao;


import com.whw.model.ExchangeRequest;
import com.whw.model.User;
import com.whw.util.Page;


import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface ExchangeRequestDao extends BaseDao<ExchangeRequest> {
    /**
     * tc start
     */

    /**
     * 根据用户编码和page查询换物请求
     * @param yhbh
     * @param page
     * @return
     */
    List<ExchangeRequest> findByUser(String yhbh, Page page);

    /**
     * tc end
     */

    //**************************************井水不犯河水****************************************
}

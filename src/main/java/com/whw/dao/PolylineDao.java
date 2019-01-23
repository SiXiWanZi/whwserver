package com.whw.dao;


import com.whw.model.Polyline;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface PolylineDao extends BaseDao<Polyline> {

    /**
     * 查询数据库表中所有的折线
     *
     * @return
     */
    List<Polyline> findAllLines();

}

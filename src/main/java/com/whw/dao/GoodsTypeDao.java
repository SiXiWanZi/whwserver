package com.whw.dao;


import com.whw.model.GoodsType;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface GoodsTypeDao extends BaseDao<GoodsType> {

    GoodsType findGoodTypeByLBBM(String lbbm);

    List<GoodsType> findFirstLevel();

    List<GoodsType> findGoodsTypeByParentId(String flbm);


}

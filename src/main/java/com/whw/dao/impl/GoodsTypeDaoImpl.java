package com.whw.dao.impl;

import com.whw.dao.GoodsTypeDao;
import com.whw.model.GoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class GoodsTypeDaoImpl extends BaseDaoImpl<GoodsType> implements GoodsTypeDao {

    @Override
    public GoodsType findGoodTypeByLBBM(String lbbm) {
        String hql = "from GoodsType gt where gt.lbbm= ?";
        List<GoodsType> list = find(hql, lbbm);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public List<GoodsType> findFirstLevel() {
        String hql = "from GoodsType gt where gt.flbm is null";
        return find(hql);
    }

    @Override
    public List<GoodsType> findGoodsTypeByParentId(String flbm) {
        String hql = "from GoodsType gt where gt.flbm is not null and gt.flbm.lbbm= ?";
        return find(hql, flbm);
    }
}

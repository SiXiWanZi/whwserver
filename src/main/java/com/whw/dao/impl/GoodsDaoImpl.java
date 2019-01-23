package com.whw.dao.impl;

import com.whw.dao.GoodsDao;
import com.whw.model.Goods;
import com.whw.model.GoodsType;
import com.whw.model.User;
import com.whw.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {


    @Override
    public Goods findGoodsByBH(String wpbh) {
        return get("from Goods g where g.wpbh = ?", wpbh);
    }

    @Override
    public List<Goods> findGoodsByGoodsType(GoodsType goodsType) {
        String hql = "from Goods g where g.goodsType.lbbm = ? " +
                "or(g.goodType.flbm is not null and g.goodsType.flbm.lbbm = ?)" +
                "or (g.goodType.flbm is not null and g.goodsType.flbm.flbm is not null and = g.goodsType.flbm.flbm.lbbm =?) order by g.fbsj des";
        return find(hql, new String[]{goodsType.getLbbm(), goodsType.getLbbm(), goodsType.getLbbm()});
    }

    @Override
    public List<Goods> findGoodsByGoodsType(GoodsType goodsType, Page page) {
        String hql = "from Goods g where g.goodsType.lbbm = ? " +
                "or(g.goodType.flbm is not null and g.goodsType.flbm.lbbm = ?)" +
                "or (g.goodType.flbm is not null and g.goodsType.flbm.flbm is not null and = g.goodsType.flbm.flbm.lbbm =?)  order by g.fbsj desc";
        return find(hql, page, new String[]{goodsType.getLbbm(), goodsType.getLbbm(), goodsType.getLbbm()});

    }

    @Override
    public List<Goods> findGoodsByUser(User user) {
        String hql = "from Goods g where g.user.yhbh = ? order by g.fbsj desc";
        return find(hql, user.getYhbh());
    }

    @Override
    public List<Goods> findGoodsByPrice(float startPrice, float endPrice) {
        String hql = "from Goods g where g.gj >=? and g.gj <= ? order by g.fbsj desc";
        return find(hql, new Object[]{startPrice, endPrice});
    }

    @Override
    public List<Goods> findGoodsByPrice(float startPrice, float endPrice, Page page) {
        String hql = "from Goods g where g.gj >=? and g.gj <= ? order by g.fbsj desc";
        return find(hql, page, new Object[]{startPrice, endPrice});
    }

    /**
     * tc start
     */

    @Override
    public List<Goods> findGoodsByUser(User user, Page page) {
        String hql = "from Goods g where g.user.yhbh = ? and g.sfyx = '1' order by g.fbsj desc";
        return find(hql, page, user.getYhbh());
    }

    @Override
    public List<Goods> findGoodsByUserFinished(User user, Page page) {
        String hql = "from Goods g where g.user.yhbh = ? and g.sfyx = '0' order by g.fbsj desc";
        return find(hql, page, user.getYhbh());
    }

    /**
     * tc end
     */

    //**************************************井水不犯河水****************************************

    /**
     * ylj start
     */
    @Override
    public List<Goods> findGoodsOrderByDate(Page page) {
        // 查询有效、未完结商品
        String hql = "from Goods g where g.sfyx='1' and g.jyzt='0' order by g.fbsj desc";
        return find(hql, page);
    }

    @Override
    public List<Goods> findGoodsOrderByCounts(Page page) {
        // 查询有效、未完结商品
        String hql = "from Goods g where g.sfyx='1' and g.jyzt='0' order by g.dzsl desc,g.plsl desc,g.xysl desc,g.fbsj desc";
        return find(hql, page);
    }

    @Override
    public List<Goods> findGoodsByIndividuation(Page page, String yhbh) {
        return null;
    }
    /**
     * ylj end
     */
}

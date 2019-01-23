package com.whw.dao;


import com.whw.model.User;

import java.util.List;

import  com.whw.model.*;
import com.whw.util.Page;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface GoodsDao extends BaseDao<Goods> {

    Goods findGoodsByBH(String wpbh);

    List<Goods> findGoodsByGoodsType(GoodsType goodsType);

    List<Goods> findGoodsByGoodsType(GoodsType goodsType, Page page);

    List<Goods> findGoodsByUser(User user);

    List<Goods> findGoodsByPrice(float startPrice, float endPrice);

    List<Goods> findGoodsByPrice(float startPrice, float endPrice, Page page);

    /**
     * tc start
     */

    /**
     * 分页查询用户已发布物品
     * @param user
     * @param page
     * @return
     */
    List<Goods> findGoodsByUser(User user, Page page);

    /**
     * 分页查询用户已完结物品
     * @param user
     * @param page
     * @return
     */
    List<Goods> findGoodsByUserFinished(User user,Page page);

    /**
     * tc end
     */

    //**************************************井水不犯河水****************************************

    /**
     * ylj start
     */
    /**
     * 分页查询“新鲜的”物品列表
     * @param page
     * @return
     */
    List<Goods> findGoodsOrderByDate(Page page);

    /**
     * 根据点赞数、评论数、想要数进行物品
     * @param page
     * @return
     */
    List<Goods> findGoodsOrderByCounts(Page page);


    /**
     * 根据用户进行个性化推荐
     * @param page
     * @return
     */
    List<Goods> findGoodsByIndividuation(Page page, String yhbh);


    /**
     * ylj end
     */
}

package com.whw.service;

import com.whw.model.Goods;

import java.util.List;

/**
 * Created by 忘尘无憾 on 2017/07/11.
 */
public interface GoodsService {
    /**
     * tc start
     */
    /**
     * 根据用户编号和当前页码获取用户已发布物品信息
     *
     * @param yhbh
     * @param currentPage
     * @return
     */
    List<Goods> getMyGoods(String yhbh, int currentPage, int pageSize);

    /**
     * 根据用户编号和当前页码获取用户已完结物品信息
     *
     * @param yhbh
     * @param currentPage
     * @return
     */
    List<Goods> getMyGoodsFinished(String yhbh, int currentPage, int pageSize);

    List<Goods> getMyWantGoods(String yhbh, int currentPage, int pageSize);

    /**
     * tc end
     */

    //**************************************井水不犯河水****************************************

    /**
     * ylj start
     */
    /**
     * 根据当前页码查询“新鲜的”物品列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Goods> getRefreshGoodsList(int currentPage, int pageSize);

    /**
     * 根据当前页码查询“猜你喜欢”物品列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Goods> getGuessYouLikeGoodsList(String yhbh, int currentPage, int pageSize);
    /**
     * ylj end
     */

}

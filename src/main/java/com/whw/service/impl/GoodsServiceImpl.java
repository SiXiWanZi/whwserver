package com.whw.service.impl;

import com.whw.dao.ExchangeRequestDao;
import com.whw.dao.GoodsDao;
import com.whw.model.ExchangeRequest;
import com.whw.model.Goods;
import com.whw.model.User;
import com.whw.service.GoodsService;
import com.whw.util.Page;
import com.whw.util.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 忘尘无憾 on 2017/07/11.
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsDao goodsDao;

    @Resource
    ExchangeRequestDao exchangeRequestDao;

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
    @Override
    public List<Goods> getMyGoods(String yhbh, int currentPage, int pageSize) {
        User user = new User();
        user.setYhbh(yhbh);
        Page page = new Page(pageSize, currentPage);
        return goodsDao.findGoodsByUser(user, page);
    }

    /**
     * 根据用户编号和当前页码获取用户已完结物品信息
     *
     * @param yhbh
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Goods> getMyGoodsFinished(String yhbh, int currentPage, int pageSize) {
        User user = new User();
        user.setYhbh(yhbh);
        Page page = new Page(pageSize, currentPage);
        return goodsDao.findGoodsByUserFinished(user, page);
    }

    /**
     * 根据用户编号和当前页码获取用户想要的物品
     *
     * @param yhbh
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Goods> getMyWantGoods(String yhbh, int currentPage, int pageSize) {
        Page page = new Page(pageSize, currentPage);
        List<ExchangeRequest> list = exchangeRequestDao.findByUser(yhbh,page);
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            goodsList.add(goodsDao.findGoodsByBH(list.get(i).getXhwpbh()));
        }
        return goodsList;
    }

    /**
     * tc end
     */

    //**************************************井水不犯河水****************************************

    /**
     * ylj start
     */

    @Override
    public List<Goods> getRefreshGoodsList(int currentPage, int pageSize) {
        Page page = new Page(pageSize, currentPage);
        return goodsDao.findGoodsOrderByDate(page);
    }

    @Override
    public List<Goods> getGuessYouLikeGoodsList(String yhbh, int currentPage, int pageSize) {
        Page page = new Page(pageSize, currentPage);
        if (yhbh != null && !"".equals(yhbh)) {
            // 用户登录后进行个性化推荐
//            return goodsDao.findGoodsOrderByDate(page);
            return goodsDao.findGoodsByIndividuation(page, yhbh);
        } else {
            // 当用户未登录时，根据点赞数、评论数、想要数进行排序推荐
            return goodsDao.findGoodsOrderByCounts(page);
        }

    }

    /**
     * ylj end
     */
}

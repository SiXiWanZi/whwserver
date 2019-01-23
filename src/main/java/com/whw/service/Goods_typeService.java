package com.whw.service;


import com.whw.model.GoodsType;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface Goods_typeService {
    /**
     * 获取所有的一级物品类别
     * @return
     */
    List<GoodsType> findFirstLevel();

    /**
     * 获取该类别的所有子类别
     * @param flbm
     * @return
     */
    List<GoodsType> findGoodsTypeByParentId(String flbm);
}

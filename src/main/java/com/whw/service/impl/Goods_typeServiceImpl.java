package com.whw.service.impl;



import com.whw.dao.GoodsTypeDao;
import com.whw.model.GoodsType;
import com.whw.service.Goods_typeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 张芳容 on 2017/07/05.
 */
@Service
@Transactional
public class Goods_typeServiceImpl implements Goods_typeService {
    @Resource
    private GoodsTypeDao goodsTypeDao;

    @Override
    public List<GoodsType> findFirstLevel() {
        return goodsTypeDao.findFirstLevel();
    }

    @Override
    public List<GoodsType> findGoodsTypeByParentId(String flbm) {
        return goodsTypeDao.findGoodsTypeByParentId(flbm);
    }
}

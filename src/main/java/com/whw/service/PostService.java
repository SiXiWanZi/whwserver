package com.whw.service;


import com.whw.model.Goods;
import com.whw.model.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface PostService {
    /**
     * 发布物品
     * @param goods
     * @return
     */
    void postGood(Goods goods);

    /**
     * 修改物品
     * @param goods
     * @return
     */
    void updateGoods(Goods goods);

    /**
     *
     * @param bh
     * @return
     */
    boolean deleteByBH(String bh);
    /**
     *
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 发布物品
     * @param userID 用户编号
     * @param files 文件组
     * @param dir   根目录
     * @param title 标题
     * @param content 描述
     * @param oldlevel 新旧程度
     * @param price 估价
     * @param goodsType 物品类型
     * @param area 所在地区
     * @param changeType 交易类型
     * @param want_change 想交换的物品
     * @return
     */
    boolean postGoods(CommonsMultipartFile[] multipartFile, String dir, String userID, String title, String content, String oldlevel, String price , String goodsType, String area, String changeType, String want_change);
}

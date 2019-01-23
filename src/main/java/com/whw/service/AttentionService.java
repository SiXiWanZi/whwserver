package com.whw.service;

import com.whw.model.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZengWangHong on 2017/07/06.
 */
public interface AttentionService {

    //曾旺红 start
    /**
     * 根据用户编号查找关注数量
     * @param id
     * @return
     */
    long findAtCountById(String id);

    /**
     * 根据用户编号查找粉丝数量
     * @param id
     * @return
     */
    long findFanCountById(String id);

    /**
     * 根据用户编号查找关注人信息
     * @param id
     * @return
     */
    List<User> findAttentionById(String id);

    /**
     * 根据用户编号查找粉丝信息
     * @param id
     * @return
     */
    List<User> findFanById(String id);
    //曾旺红 end
}

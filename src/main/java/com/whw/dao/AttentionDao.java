package com.whw.dao;


import com.whw.model.Attention;
import com.whw.model.User;
import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface AttentionDao extends BaseDao<Attention> {

    /**
     * 查询关注了谁
     * @return 用户Id
     */
    List<Attention> findBGZUserByUser(User user);

    /**
     * 查询谁关注了自己
     * @param user
     * @return
     */
    List<Attention> findGZUserByUser(User user);

    /**
     * 取消关注
     * @param user 用户
     * @param cancelUser 取消关注对象
     */
    void cancelAttention(String user, String cancelUser);

    void cancelAttention(User user, User cancelUser);

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
    //曾旺红 end
}

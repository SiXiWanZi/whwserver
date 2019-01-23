package com.whw.dao;


import com.whw.model.Star;
import com.whw.model.User;
import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface StarDao extends BaseDao<Star> {

    /**
     * 根据用户编号和物品编号查找物品
     * @param yhbh
     * @param wpbh
     * @return
     */
    List<Star> findByYhbhAndWpbh(String yhbh, String wpbh);

    /**
     * 根据用户和物品编号查找物品
     * @param user
     * @param wpbh
     * @return
     */
    List<Star> findByUserAndWpbh(User user, String wpbh);

    /**
     * 根据用户编号和集市编号查找集市
     * @param user
     * @param jsbh
     * @return
     */
    List<Star> findByYhbhAndJsbh(String user, String jsbh);

    /**
     * 根据用户和集市编号查找物品
     * @param user
     * @param jsbh
     * @return
     */
    List<Star> findByUserAndJsbh(User user, String jsbh);

    /**
     * 取消对物品的收藏
     * @param user
     * @param wpbh
     */
    void cancelStarWp(String user, String wpbh);

    /**
     * 取消对集市的收藏
     * @param user
     * @param jsbh
     */
    void cancelStarJs(String user, String jsbh);


}

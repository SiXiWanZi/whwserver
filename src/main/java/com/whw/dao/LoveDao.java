package com.whw.dao;


import com.whw.model.Love;
import com.whw.model.User;
import oracle.jrockit.jfr.StringConstantPool;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface LoveDao extends BaseDao<Love> {

    int countLoveByWpbh(String wpbh);

    int countLoveByJsbh(String jsbh);

    List<Love> findByWpbh(String wpbh);

    List<Love> findByJsbh(String jsbh);

    /**
     * 取消对物品的点赞
     * @param user
     * @param wpbh
     */
    void cancelLoveWp(String user, String wpbh);

    /**
     * 取消对集市的点赞
     * @param user
     * @param jsbh
     */
    void cancelLoveJs(String user, String jsbh);



}

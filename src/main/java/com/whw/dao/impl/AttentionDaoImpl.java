package com.whw.dao.impl;

import com.whw.dao.AttentionDao;
import com.whw.dao.UserDao;
import com.whw.model.Attention;
import com.whw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class AttentionDaoImpl extends BaseDaoImpl<Attention> implements AttentionDao {


    @Override
    public List<Attention> findBGZUserByUser(User user) {
        String hql = "from Attention a where a.yhbh = ? order by a.gzsj";
        return find(hql,user.getYhbh());
    }

    @Override
    public List<Attention> findGZUserByUser(User user) {
        String hql = "from Attention a where a.bgzyhbh = ? order by a.gzsj";
        return find(hql,user.getYhbh());
    }

    @Override
    public void cancelAttention(String user, String cancelUser) {
        String hql = "from Attention a where a.yhbh=? and a.bgzyhbh = ?";
        Attention a = get(hql,user,cancelUser);
        if(a!=null){
            delete(a);
        }
    }

    @Override
    public void cancelAttention(User user, User cancelUser) {
        cancelAttention(user.getYhbh(),cancelUser.getYhbh());
    }

    @Override
    public long findAtCountById(String id) {
        String hql="select count(*) from Attention a where a.yhbh = ?";
        return count(hql,id);
    }

    @Override
    public long findFanCountById(String id) {
        String hql="select count(*) from Attention a where a.bgzyhbh = ?";
        return count(hql,id);
    }
}

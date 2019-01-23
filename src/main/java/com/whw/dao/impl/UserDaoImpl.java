package com.whw.dao.impl;

import com.whw.dao.UserDao;
import com.whw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User findByName(String name) {
        String hql = "from User where NC = ?";
        return get(hql, name);
    }

    @Override
    public User findById(String yhbh) {
        String hql = "from User where YHBH = ?";
        return get(hql, yhbh);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "from User where YX = ?";
        return get(hql, email);
    }

    @Override
    public void deleteById(String id) {
        delete(findById(id));
    }

    @Override
    public void addUser(User user) {
        save(user);
    }

    @Override
    public void updateMyUserPasswordById(String id, String password) {
        User myuser = findById(id);
        myuser.setMm(password);
        saveOrUpdate(myuser);
    }

    @Override
    public User findByAccountAndPassword(String name, String password) {
        String hql = "from User where NC = ? and  MM = ?";
        return get(hql, new Object[]{name, password});
    }

    @Override
    public void updateUserInfo(User user) {
        saveOrUpdate(user);
    }

    @Override
    public void updateUserToken(String id, String token) {
        User myuser = findById(id);
        myuser.setToken(token);
        saveOrUpdate(myuser);
    }

    @Override
    public List<User> findAttentionById(String id) {
        String hql = "from User where yhbh \n" +
                "in\n" +
                "(select bgzyhbh from Attention where yhbh = ? ORDER BY gzsj desc)";
        return find(hql, id);
    }

    @Override
    public List<User> findFanById(String id) {
        String hql = "from User where yhbh \n" +
                "in\n" +
                "(select yhbh from Attention where bgzyhbh = ? ORDER BY gzsj desc)";
        return find(hql, id);
    }
}

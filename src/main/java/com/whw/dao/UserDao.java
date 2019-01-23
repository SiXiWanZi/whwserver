package com.whw.dao;


import java.util.List;
import com.whw.model.User;
/**
 * Created by YangZhi on 2016/1/15.
 */
public interface UserDao extends BaseDao<User> {
    /**
     * 通过名字查找用户
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 通过id
     * @param yhbh
     * @return
     */
    User findById(String yhbh);

    /**
     * 通过邮箱查找用户
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 通过ID删除用户
     * @param id
     */
    void deleteById(String id);

    /**
     *
     * 增加一个用户
     * @param user
     */
    void addUser(User user);

    /**
     * 更新用户密码
     * @param id
     * @param password
     */
    void updateMyUserPasswordById(String id, String password);

    /**
     * 按账户密码查询用户
     * @param name
     * @param password
     * @return
     */
    User findByAccountAndPassword(String name, String password);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 更新用户token
     * @param id
     * @param token
     */
    void updateUserToken(String id,String token);
    //曾旺红 start
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

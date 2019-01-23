package com.whw.service;

import com.whw.model.User;

/**
 * Created by 忘尘无憾 on 2017/07/05.
 */
public interface UserService {
    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */
    User login(String name, String password);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    boolean reg(User user)throws Exception;

    /**
     * 更新
     *
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 更新密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePass(String id, String oldPassword, String newPassword);

    /**
     * 根据id找到用户
     *
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 根据昵称找到用户
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(String id);
}

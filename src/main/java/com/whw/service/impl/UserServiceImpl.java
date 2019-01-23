package com.whw.service.impl;

import com.whw.dao.GoodsDao;
import com.whw.dao.GoodsTypeDao;
import com.whw.dao.UserDao;
import com.whw.model.Goods;
import com.whw.model.User;
import com.whw.service.PostService;
import com.whw.service.UserService;
import com.whw.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 张芳容 on 2017/07/05.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public User login(String name, String password) {
        return null;
    }

    @Override
    public boolean reg(User user) throws Exception {
        return false;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean updatePass(String id, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }
}

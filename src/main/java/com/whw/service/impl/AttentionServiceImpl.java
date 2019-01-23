package com.whw.service.impl;

import com.whw.dao.AttentionDao;
import com.whw.dao.UserDao;
import com.whw.model.Attention;
import com.whw.model.User;
import com.whw.service.AttentionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZengWangHong on 2017/07/06.
 */
@Service
@Transactional
public class AttentionServiceImpl implements AttentionService{

    @Resource
    private AttentionDao attentionDao;
    @Resource
    private UserDao userDao;

    @Override
    public long findAtCountById(String id) {
        return attentionDao.findAtCountById(id);
    }

    @Override
    public long findFanCountById(String id) {
        return attentionDao.findFanCountById(id);
    }

    @Override
    public List<User> findAttentionById(String id) {
        return userDao.findAttentionById(id);
    }

    @Override
    public List<User> findFanById(String id) {
        return userDao.findFanById(id);
    }
}

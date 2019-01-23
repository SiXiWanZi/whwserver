package com.whw.service.impl;

import com.whw.dao.FeedBackDao;
import com.whw.model.FeedBack;
import com.whw.service.FeedbackService;
import com.whw.util.SendmailUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 张芳容 on 2017/07/13.
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{
    @Resource
    FeedBackDao feedBackDao;
    @Override
    public void SendFeedback(String userID, String content){
        FeedBack feedBack=new FeedBack();
        feedBack.setNr(content);
        feedBack.setYhbh(userID);
        feedBack.setFksj(new Date());
        //1.保存信息到数据库
        feedBackDao.save(feedBack);
        //2.发送邮件给开发者
        SendmailUtil.senEmail(content);
    }
}

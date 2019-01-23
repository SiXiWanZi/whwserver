package com.whw.service;

/**
 * Created by 张芳容 on 2017/07/13.
 */
public interface FeedbackService {
    /**
     * 发送反馈记录
     *
     * @param userID  用户编号
     * @param content 反馈内容
     * @return
     */
    void SendFeedback(String userID, String content);
}

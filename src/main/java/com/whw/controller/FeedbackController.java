package com.whw.controller;

import com.whw.service.FeedbackService;
import com.whw.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by YangZhi on 2017/06/05.
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    public FeedbackService feedbackService;

    private Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    /**
     * 发送反馈
     *
     * @param userID
     * @param feedContent
     * @return
     */
    ///如果只需要POST 请求, 直接 使用 method=RequestMethod.POST
    @RequestMapping(value = "sentFeedback", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String login(String userID, String feedContent) {
        try {
            feedbackService.SendFeedback(userID, feedContent);
            return ResultUtil.BackResult(true, "反馈成功");
        } catch (Exception e) {
            return ResultUtil.BackResult(false, "反馈失败");
        }
    }
}

package com.whw.controller;

import com.whw.model.User;
import com.whw.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService myUserService;

    @Resource
    public UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */
    ///如果只需要POST 请求, 直接 使用 method=RequestMethod.POST
    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String login(String name, String password) {
        logger.error("login" + name + password);
        //通过用户名查找用户
        User user = userService.findByName(name);
        //如果用户不存在
        if (user == null) {
            return ResultUtil.BackResult(false, "用户不存在，请注册");
        }
        //验证用户名和密码是否一样
        if (user.getMm().equals(password)) {
            return ResultUtil.BackResult(true, user, "登录成功");
        } else {
            return ResultUtil.BackResult(false, "密码有误，请重新输入");
        }
    }

    /**
     * 注册用户
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "reg", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String reg(String name, String password) {
        logger.error("reg:" + name + "---" + password);
        User user = new User();
        user.setNc(name);
        user.setMm(password);
        try {
            if (userService.reg(user)) {
                return ResultUtil.BackResult(true, "注册成功");
            }
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return ResultUtil.BackResult(false, "该用户已存在");
    }

    /**
     * 更新用户
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String update(String name, String password) {
        return null;
    }
}

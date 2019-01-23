package com.whw.controller;

import com.alibaba.fastjson.JSON;
import com.whw.model.Goods;
import com.whw.model.User;
import com.whw.service.AttentionService;
import com.whw.service.GoodsService;
import com.whw.service.UserService;
import com.whw.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 忘尘无憾 on 2017/07/11.
 */
@Controller
@RequestMapping("/personal")
public class PersonalInfoController {

    @Resource
    GoodsService goodsService;

    @Resource
    public UserService userService;

    @Resource
    public AttentionService attentionService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * tc start
     */

    /**
     * 根据用户编号和当前页码查询用户已发布物品
     *
     * @param yhbh
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "mygoods", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getMyGoods(String yhbh, int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        logger.error("mygoods:" + yhbh + "----" + currentPage);
        List<Goods> list = goodsService.getMyGoods(yhbh, currentPage, pageSize);
        return backGoodsData(list);
    }

    /**
     * 根据用户编号和当前页码查询用户已完结物品
     *
     * @param yhbh
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "mygoods_finished", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getMyGoodsFinished(String yhbh, int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        logger.error("mygoods_finished:" + yhbh + "----" + currentPage);
        List<Goods> list = goodsService.getMyGoodsFinished(yhbh, currentPage, pageSize);
        return backGoodsData(list);
    }

    /**
     * 根据用户编号和当前页码查询用户已完结物品
     *
     * @param yhbh
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "mywant", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getMyWantGoods(String yhbh, int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        logger.error("mywant:" + yhbh + "----" + currentPage);
        List<Goods> list = goodsService.getMyWantGoods(yhbh, currentPage, pageSize);
        return backGoodsData(list);
    }

    /**
     * 返回物品json数据格式
     *
     * @param list
     * @return
     */
    private String backGoodsData(List<Goods> list) {
        if (list.size() == 0) {
            return ResultUtil.BackResult(false, "已经到底了");
        }
        return ResultUtil.BackResult(true, list);
    }

    /**
     * tc end
     */

    //**************************************井水不犯河水****************************************

    //曾旺红 start
    @RequestMapping(value = "maininfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getMainInfo(String id) {
        logger.error("maininfo" + id);
        //通过ID 查找用户信息
        User user = userService.findById(id);
        //通过ID查找粉丝量和关注量
        long fanCount = attentionService.findFanCountById(id);
        long attenCount = attentionService.findAtCountById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        map.put("fanNum", fanCount);
        map.put("attenNum", attenCount);
        return ResultUtil.BackResult(true, map, "返回成功");
    }

    @RequestMapping(value = "personaldata", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getPersonalData(String id) {
        logger.error("personaldata" + id);
        //通过ID 查找用户信息
        User user = userService.findById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        return ResultUtil.BackResult(true, map, "返回成功");
    }

    @RequestMapping(value = "modifydata", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String modifyPersonalData(String str) {
        logger.error("modifydata" + str.toString());
        User user = (User) JSON.parseObject(str, User.class);
        //更新用户信息
        User user1 = userService.findById(user.getYhbh());//查询数据库中的数据
        Field[] userFd = user.getClass().getDeclaredFields(); //获取到user中的所有字段
        for (Field f : userFd) {
            f.setAccessible(true);
            Object obj = null;
            try {
                obj = f.get(user);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                try {
                    f.set(user1, obj);//将obj设置到user1对象中
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //更新user1到数据库中
        userService.update(user1);

//        String id=user.getYhbh();
//        User u=userService.findById(id);
//        u.setNc(user.getNc());
//        u.setCsny(user.getCsny());
//        u.setYhdz(user.getYhdz());
//        u.setXb(user.getXb());
//        u.setLxdh(user.getLxdh());
//        u.setYx(user.getYx());
//        u.setGxqm(user.getGxqm());
//        userService.update(u);
        return ResultUtil.BackResult(true, "更新成功");
    }

    @RequestMapping(value = "attentionlist", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getFanList(String id) {
        try {
            logger.error("attentionlist" + id.toString());
            List<User> list = attentionService.findAttentionById(id);
            return ResultUtil.BackResult(true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "获取关注人信息失败");
        }
    }


    @RequestMapping(value = "followlist", method = {RequestMethod.POST, RequestMethod.GET})
    public String getFollowList(String id) {
        try {
            logger.error("followlist" + id.toString());
            List<User> list = attentionService.findFanById(id);
            return ResultUtil.BackResult(true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "获取粉丝信息失败");
        }
    }
    //曾旺红 end
}

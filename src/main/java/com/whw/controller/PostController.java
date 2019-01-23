package com.whw.controller;

import com.whw.service.PostService;
import com.whw.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 张芳容 on 2017/07/05.
 */
@Controller
@RequestMapping("/postGoods")
public class PostController {
    @Resource
    public PostService postService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 图像上传
     */
    @RequestMapping("/postgood")
    @ResponseBody
    public String uploadPhoto(@RequestParam("multipartFile") CommonsMultipartFile[] multipartFile, HttpServletRequest request) {
        try {
            if (multipartFile == null || multipartFile.length == 0) {
                System.out.println("没有图片");
            }
            String userID = request.getParameter("userID");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String oldlevel = request.getParameter("oldlevel");
            String price = request.getParameter("price");
            String goodsType = request.getParameter("goodsType");
            String area = request.getParameter("area");
            String changeType = request.getParameter("changeType");
            String want_change = request.getParameter("want_change");
            //根路径
            String dir = request.getSession().getServletContext().getRealPath("/upload_img");
            //保存
            boolean b = postService.postGoods(multipartFile, dir, userID, title, content, oldlevel, price, goodsType, area, changeType, want_change);
            if (b) {
                return ResultUtil.BackResult(true, "发布成功");
            }
            return ResultUtil.BackResult(false, "发布失败");
        } catch (Exception e) {
            if (e != null) e.printStackTrace();
            return ResultUtil.BackResult(false, "发布失败");
        }

    }
}

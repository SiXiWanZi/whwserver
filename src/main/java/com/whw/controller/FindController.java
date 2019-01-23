package com.whw.controller;

import com.whw.model.Goods;
import com.whw.service.GoodsService;
import com.whw.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylj on 2017/06/05.
 */
@Controller
@RequestMapping("/find")
public class FindController {
    @Resource
    public GoodsService goodsService;

    private Logger logger = LoggerFactory.getLogger(FindController.class);

    /**
     * 根据当前页码查询发现页面的物品列表
     *
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "goodsList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getGoodsList(String type, String currentPage, @RequestParam(defaultValue = "10") String pageSize, String yhbh) {
        System.out.println("type:"+type+";currentPage="+currentPage+";pageSize:"+pageSize+",yhbh"+yhbh);
        try {
            List<Goods> list=new ArrayList<>();
            if(type!=null){
                switch (type) {
                    case "新鲜的":
                        list= goodsService.getRefreshGoodsList(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
                        System.out.println("新鲜的list.size()"+list.size());
                        break;
                    case "猜你喜欢":
                        list = goodsService.getGuessYouLikeGoodsList(yhbh, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
                        System.out.println("猜你喜欢list.size()"+list.size());
                        break;
                }
            }
            return ResultUtil.BackResult(true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "网络异常请稍后重试");
        }

    }

}

package com.whw.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whw.model.GoodsType;
import com.whw.service.Goods_typeService;
import com.whw.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张芳容 on 2017/07/05.
 */
@Controller
@RequestMapping("/GetGoods_type")
public class Goods_typeController {
    @Resource
    public Goods_typeService goods_typeService;

    private Logger logger = LoggerFactory.getLogger(Goods_typeController.class);

    /**
     * 获取物品类别
     */
    @RequestMapping("/GetGoods_type")
    @ResponseBody
    public String uploadPhoto() {
        try {
            List<GoodsType> fistLevel = goods_typeService.findFirstLevel();
            List<List<GoodsType>> secondLevel = new ArrayList<List<GoodsType>>();
            for (int i = 0; i < fistLevel.size(); i++) {
                List<GoodsType> child = goods_typeService.findGoodsTypeByParentId(fistLevel.get(i).getLbbm());
                secondLevel.add(child);
            }
            //System.out.println(JSON.toJSONString(fistLevel)+"*****"+JSON.toJSONString(secondLevel,, SerializerFeature.DisableCircularReferenceDetect));
            return ResultUtil.BackResult(true, JSON.toJSONString(fistLevel, SerializerFeature.DisableCircularReferenceDetect), JSON.toJSONString(secondLevel, SerializerFeature.DisableCircularReferenceDetect));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "获取物品分类信息失败");
        }

    }
}

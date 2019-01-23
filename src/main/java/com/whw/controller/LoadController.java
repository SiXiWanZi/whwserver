package com.whw.controller;

import com.whw.model.Edge;
import com.whw.model.Goods;
import com.whw.model.MyPolyLine;
import com.whw.model.Node;
import com.whw.service.LoadService;
import com.whw.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 地图加载时需要的数据
 */
@Controller
@RequestMapping("/load")
public class LoadController {
    @Resource
    public LoadService loadService;

    /**
     * 得到所有的节点
     *
     * @return
     */
    @RequestMapping(value = "myPoints", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getAllPoints() {
        try {
            List<Node> list = loadService.getAllpoints();
            return ResultUtil.BackResult(true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "网络异常请稍后重试");
        }
    }

    /**
     * 得到所有的路段（包括手动采集的+百度地图原有的）
     *
     * @return
     */
    @RequestMapping(value = "myRoads", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getAllRoads() {
        try {
            List<Edge> list = loadService.getAllRoads();
            return ResultUtil.BackResult(true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "网络异常请稍后重试");
        }
    }


    /**
     * 得到所有折线
     *
     * @return
     */
    @RequestMapping(value = "myLines", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getAllLines() {
        try {
            List<MyPolyLine> list = loadService.getAllLines();
            return ResultUtil.BackResult(true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "网络异常请稍后重试");
        }
    }


}

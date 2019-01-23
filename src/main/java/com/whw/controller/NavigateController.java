package com.whw.controller;

import com.whw.model.Car;
import com.whw.model.NavigationResult;
import com.whw.service.NavigateService;
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
 * 导航相关
 */
@Controller
@RequestMapping("/nav")
public class NavigateController {
    @Resource
    public NavigateService navigateService;

    private Logger logger = LoggerFactory.getLogger(NavigateController.class);

    /**
     * 得到起点到终点间的前 K 条最短路径
     *
     * @return
     */
    @RequestMapping(value = "kRoads", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getKRoads(String startNodeId, String endNodeId) {
        try {
//            List<Edge> list = loadService.getAllRoads();
//            return ResultUtil.BackResult(true, list);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "网络异常请稍后重试");
        }
    }

    /**
     * 得到起点到终点间的最短路径（A*）
     *
     * @return
     */
    @RequestMapping(value = "sp_AStar", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getShortestRoad_AStar(String startNodeId, String endNodeId) {
        try {
//            List<Edge> list = loadService.getAllRoads();
//            return ResultUtil.BackResult(true, list);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "网络异常请稍后重试");
        }
    }

    /**
     * 多车辆同时进行路径规划
     *
     * @return
     */
    @RequestMapping(value = "multiAgentNav", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getMultiAgentNavResult(String agentsNavRequest) {
        try {
            System.out.println("agentsNavRequest:"+agentsNavRequest);
            // 将请求的gson串转化成对象
//            List<Car> cars= GsonUtil.GsonToList(agentsNavRequest,Car.class);
            // 假设有一百个车辆请求路径
            List<Car> cars = new ArrayList<>();
            for(int i=0;i<100;i++) {
                Car car = new Car();
                car.setCarId(i + "");
                car.setStartNodeId("183");
                car.setEndNodeId("8");
                cars.add(car);
            }
            List<NavigationResult> result= navigateService.getMultiAgentNavResult_cluster(cars);
            String r = ResultUtil.BackResult(true, result);
            return r;
//            return ResultUtil.BackResult(true, "hello");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.BackResult(false, "网络异常请稍后重试");
        }
    }


}

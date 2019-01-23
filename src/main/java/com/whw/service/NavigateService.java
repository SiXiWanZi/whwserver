package com.whw.service;

import com.whw.model.Car;
import com.whw.model.MyGraph;
import com.whw.model.NavPath;
import com.whw.model.NavigationResult;
import com.whw.service.impl.NavigateServiceImpl;

import java.util.List;

/**
 * 导航相关模块
 */
public interface NavigateService {
    // 得到两点间最短路径
    NavPath getShortestRoad_AStar(String startNodeId, String endNodeId);

    // 得到两点间最短路径
    NavPath getShortestRoad_Dijkstra(String startNodeId, String endNodeId);

    // 得到K条最短路径
    List<NavPath> getKRoads(MyGraph g, String startNodeId, String endNodeId);

    // 多车辆同时选路，得到其对应所选路径
    List<NavigationResult> getMultiAgentNavResult_cluster(List<Car> cars);

        // 根据各车选路情况得到系统Q值
    double getQ(MyGraph g, List<Car> cars);

    NavigationResult clusterIteration(MyGraph g, List<NavigationResult> preAll, List<Car> cluster);

    NavigationResult tripIteration(int t, MyGraph g, List<NavigationResult> preAll);


    double[] getSelectionProb_traditional(MyGraph g, Car car, List<Car> pre);

    List<NavigateServiceImpl.MyCluster> dividIntoGroups(int t, NavigationResult res);

    // 不分集群的迭代方式
    List<NavigationResult> getMultiAgentNavResult_noCluster(List<Car> cars);
}

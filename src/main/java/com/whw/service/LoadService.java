package com.whw.service;

import com.whw.model.*;

import java.util.List;

/**
 * Created by 忘尘无憾 on 2017/07/11.
 */
public interface LoadService {
    // 得到所有路段包括连通，和不连通
    List<Edge> getAllRoads();

    List<Node> getAllpoints();

    MyGraph createGraph_fromDb();

    // 得到连通的路段
    List<Edge> getConnectedRoads();

    // 得到数据库中所有的折线
    List<MyPolyLine> getAllLines();

}

package com.whw.service.impl;

import com.whw.dao.EdgeDao;
import com.whw.dao.NodeDao;
import com.whw.dao.PolylineDao;
import com.whw.model.*;
import com.whw.service.LoadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 地图加载时需要的数据
 */
@Service
@Transactional
public class LoadServiceImpl implements LoadService {

    @Resource
    EdgeDao edgeDao;

    @Resource
    NodeDao nodeDao;

    @Resource
    PolylineDao polylineDao;

    @Override
    public List<Edge> getAllRoads() {
        return edgeDao.findAllRoads();
    }

    @Override
    public List<Node> getAllpoints() {
        return nodeDao.findAllPoints();
    }

    /**
     * 根据数据库中数据在内存中创建图
     * @return
     */
    @Override
    public MyGraph createGraph_fromDb() {
        MyGraph g = new MyGraph();
        // 节点
        List<Node> nodes = getAllpoints();
        g.nodes = nodes.toArray(new Node[nodes.size()]);
        g.n = nodes.size();
        // 边
        List<Edge> edges = getConnectedRoads();
        /*Random r = new Random();
        for (Edge edge : edges) {
            // 初始化路段的自由流行驶时间
            edge.setInitialTime(edge.getLength());
            // 路段的通行能力
            edge.setInitalCarNumbers(r.nextInt(1)*60+40);
        }*/
        g.edges = edges.toArray(new Edge[edges.size()]);
        g.e = edges.size();
        // 关联边数组
        Arrays.sort(g.nodes);
        Arrays.sort(g.edges);

        g.linkedEdges = new int[g.e];
        int k = 0;// linkedEdges[]中可用的最新位置
        int index = 0;// 上一节点搜索结束后在弧集中的索引
        for (int i = 0; i < g.n; i++) {
            // 对于每一个节点：从弧集中找出从该节点发出的弧
            Node node = g.nodes[i];
            int count = 0;
            int beginIndex = k;
            while (index < g.edges.length && g.edges[index].getEnterNode().getNodeId() ==
                    node.getNodeId()) {
                // 如果是该节点发出的弧
                count++;// 个数加1
//                g.linkedEdges[k++]=g.edges[index].edgeId;// 将该弧存起来
                g.linkedEdges[k++] = index;// 将该弧在数组中的下标存起来
                index++;// 判断下一个弧如何
            }
            // 所有的弧都判断完后
            if (count == 0) {
                // 该节点没有任何弧发出
                node.setCount(0);
                node.setLinkEdgesBeginIndex(-1);
            } else {
                node.setCount(count);
                node.setLinkEdgesBeginIndex(beginIndex);
            }
        }
        return g;
    }

    @Override
    public List<Edge> getConnectedRoads() {
        return edgeDao.findConnectedEdges();
    }


    // 得到所有折线
    @Override
    public List<MyPolyLine> getAllLines() {
        List<MyPolyLine> result = new ArrayList<>();
        // 从数据库中查询出所有的折线
        List<Polyline> list = polylineDao.findAllLines();
        for (Polyline polyline : list) {
            // 将数据库中的折线转化为自定义的折线Bean
            MyPolyLine line = new MyPolyLine();
            // 设置折线id
            line.setLineId(polyline.getLineId());
            // 设置折线上的各个点
            List<Node> nodes = new ArrayList<>();
            String s = polyline.getPoints();
            if (s != null && s.length() != 0) {
                // 点字符串不为空
                String[] pointsNodeId = s.split(",");
                System.out.println("pointsNodeId:"+Arrays.toString(pointsNodeId));
                for(int i=0;i<pointsNodeId.length;i++) {
                    Node node = nodeDao.findById(pointsNodeId[i]);
                    nodes.add(node);
                }
            }
            line.setPoints(nodes);
            result.add(line);
        }
        return result;
    }
}

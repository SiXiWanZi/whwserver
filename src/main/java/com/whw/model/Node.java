package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 节点
 */
@Entity
@Table(name = "node")
public class Node implements Serializable,Comparable<Node> {

    /**
     * 节点编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "nodeId", length = 32,columnDefinition = "char(32)")
    private String nodeId;

    /**
     * 节点名称
     */
    @Column(name = "nodeName", length = 20,columnDefinition = "varchar(20)")
    private String nodeName;

    /**
     * 经度
     */
    @Column(name = "longtitude",length=10, columnDefinition = "double(10,7)")
    private double longtitude;

    /**
     * 纬度
     */
    @Column(name = "latitude", length=10,columnDefinition = "double(10,7)")
    private double latitude;

    /**
     * 节点类型
     */
    @Column(name="type",length = 20,columnDefinition = "varchar(20)")
    private String type;

    /**
     * 是否有红绿灯
     */
    @Column(name = "hasLed", length = 1,columnDefinition = "tinyint(1)")
    private boolean hasLed;

    /**
     * 是否手动采集
     */
    @Column(name = "fromManual", length = 1,columnDefinition = "tinyint(1)")
    private boolean fromManual;

    /**
     * 从该节点发出的弧个数
     */
    @Column(name = "count", length = 11, columnDefinition = "INT default 0")
    private int count;

    /**
     * 该节点发出的第一个弧在linkEdges中的下标
     */
    @Column(name = "linkEdgesBeginIndex", length = 11, columnDefinition = "INT default 0")
    private int linkEdgesBeginIndex;

    // 该节点处的转向限制
    @OneToMany(mappedBy = "nodeId",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    public List<ForbiddenTurn> forbiddenTurnList= new ArrayList<>();

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasLed() {
        return hasLed;
    }

    public void setHasLed(boolean hasLed) {
        this.hasLed = hasLed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLinkEdgesBeginIndex() {
        return linkEdgesBeginIndex;
    }

    public void setLinkEdgesBeginIndex(int linkEdgesBeginIndex) {
        this.linkEdgesBeginIndex = linkEdgesBeginIndex;
    }

    public List<ForbiddenTurn> getForbiddenTurnList() {
        return forbiddenTurnList;
    }

    public void setForbiddenTurnList(List<ForbiddenTurn> forbiddenTurnList) {
        this.forbiddenTurnList = forbiddenTurnList;
    }

    public boolean isFromManual() {
        return fromManual;
    }

    public void setFromManual(boolean fromManual) {
        this.fromManual = fromManual;
    }


    @Override
    public String toString() {
        return "Node{" +
                "nodeId='" + nodeId + '\'' +
                ", nodeName='" + nodeName + '\'' +
                '}';
    }

    public Node(){}

    public Node(String nodeId) {
        this.nodeId = nodeId;
    }

    public Node(String nodeName, double longtitude, double latitude, String type, boolean hasLed) {
        this.nodeName = nodeName;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.type = type;
        this.hasLed = hasLed;
    }

    @Override
    public int compareTo(Node o) {
        if (o == null) return -1;
        return this.nodeId.compareTo(o.nodeId);
    }
}
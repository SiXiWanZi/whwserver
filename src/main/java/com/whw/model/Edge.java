package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 边
 */
@Entity
@Table(name = "edge")
public class Edge implements Serializable, Comparable<Edge> {

    /**
     * 边编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "edgeId", length = 32, columnDefinition = "char(32)")
    private String edgeId;


    /**
     * 边名称
     */
    @Column(name = "edgeName", length = 40, columnDefinition = "varchar(40)")
    private String edgeName;

    /**
     * 长度
     */
    @Column(name = "length", length = 10, columnDefinition = "double(10,7)")
    private double length;


    /**
     * 宽度
     */
    @Column(name = "width", length = 10, columnDefinition = "double(10,7)")
    private double width;

    /**
     * 车道数
     */
    @Column(name = "laneNum", length = 11, columnDefinition = "INT default 0")
    private int laneNum;

    /**
     * 起始节点
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterNodeId", updatable = false)
    private Node enterNode;

    /**
     * 终止节点
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exitNodeId", updatable = false)
    private Node exitNode;

    /**
     * 权重
     */
//    @Column(name = "weight", length = 10, columnDefinition = "double(10,7)")
//    private double weight;


    /**
     * 是否连通
     */
    @Column(name = "isConnected", length = 1, columnDefinition = "tinyint(1)")
    private boolean isConnected;

    /**
     * 是否是手动采集的
     */
    @Column(name = "fromManual", length = 1, columnDefinition = "tinyint(1)")
    private boolean fromManual;

    /**
     * 自由流通行时间：以min为单位
     */
    @Column(name = "initialTime", length = 10, columnDefinition = "double(10,7)")
    private Double initialTime;

    public double getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(double initialTime) {
        this.initialTime = initialTime;
    }

    /**
     * 初始通行能力 pub/min
     */
    @Column(name = "initalCarNumbers", length = 11, columnDefinition = "INT default 0")
    private int initalCarNumbers;

    /**
     * 各路段上当前的车辆数
     */
    @Column(name = "curCarNumbers", length = 11, columnDefinition = "INT default 0")
    private int curCarNumbers;

    /**
     * 当前路段流量下通行时间：以min为单位
     */
    @Column(name = "curRunTime", length = 10, columnDefinition = "double(10,7)")
    private Double curRunTime;

    public void setInitialTime(Double initialTime) {
        this.initialTime = initialTime;
    }

    public Double getCurRunTime() {
        return curRunTime;
    }

    public void setCurRunTime(Double curRunTime) {
        this.curRunTime = curRunTime;
    }

    public int getInitalCarNumbers() {
        return initalCarNumbers;
    }

    public void setInitalCarNumbers(int initalCarNumbers) {
        this.initalCarNumbers = initalCarNumbers;
    }

    /**
     * point1编号
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point1", updatable = false)
    private Node point1;

    /**
     * point2编号
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point2", updatable = false)
    private Node point2;

    /**
     * point3编号
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point3", updatable = false)
    private Node point3;

    /**
     * point4编号
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point4", updatable = false)
    private Node point4;

    public String getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(String edgeId) {
        this.edgeId = edgeId;
    }

    public String getEdgeName() {
        return edgeName;
    }

    public void setEdgeName(String edgeName) {
        this.edgeName = edgeName;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getLaneNum() {
        return laneNum;
    }

    public void setLaneNum(int laneNum) {
        this.laneNum = laneNum;
    }

    public Node getEnterNode() {
        return enterNode;
    }

    public void setEnterNode(Node enterNode) {
        this.enterNode = enterNode;
    }

    public Node getExitNode() {
        return exitNode;
    }

    public void setExitNode(Node exitNode) {
        this.exitNode = exitNode;
    }

    public boolean getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean isFromManual() {
        return fromManual;
    }

    public void setFromManual(boolean fromManual) {
        this.fromManual = fromManual;
    }

    public Node getPoint1() {
        return point1;
    }

    public void setPoint1(Node point1) {
        this.point1 = point1;
    }

    public Node getPoint2() {
        return point2;
    }

    public void setPoint2(Node point2) {
        this.point2 = point2;
    }

    public Node getPoint3() {
        return point3;
    }

    public void setPoint3(Node point3) {
        this.point3 = point3;
    }

    public Node getPoint4() {
        return point4;
    }

    public void setPoint4(Node point4) {
        this.point4 = point4;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public int getCurCarNumbers() {
        return curCarNumbers;
    }

    public void setCurCarNumbers(int curCarNumbers) {
        this.curCarNumbers = curCarNumbers;
    }


    public void addCurCarNumbers() {
        curCarNumbers++;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "edgeId='" + edgeId + '\'' +
                ", edgeName='" + edgeName + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", laneNum=" + laneNum +
                ", enterNode=" + enterNode +
                ", exitNode=" + exitNode +
                ", isConnected=" + isConnected +
                ", fromManual=" + fromManual +
                ", initialTime=" + initialTime +
                ", initalCarNumbers=" + initalCarNumbers +
                ", curCarNumbers=" + curCarNumbers +
                ", curRunTime=" + curRunTime +
                ", point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                ", point4=" + point4 +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        if (o == null) {
            return -1;
        }
        return this.enterNode.getNodeId().compareTo(o.enterNode.getNodeId());
    }
}
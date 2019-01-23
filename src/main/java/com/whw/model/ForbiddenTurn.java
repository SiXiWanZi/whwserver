package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 禁行关系
 */
@Entity
@Table(name = "forbiddenturn")
public class ForbiddenTurn implements Serializable {

    /**
     * 进行关系编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "forbiddenId", length = 32, columnDefinition = "char(32)")
    private String forbiddenId;


    /**
     * 禁行节点编号
     */
    @Column(name = "nodeId", length = 32,columnDefinition = "char(32)")
    private String nodeId;

    /**
     * 驶入节点编号
     */
    @Column(name = "enterNodeId", length = 32,columnDefinition = "char(32)")
    private String enterNodeId;


    /**
     * 驶出节点编号
     */
    @Column(name = "exitNodeId", length = 32,columnDefinition = "char(32)")
    private String exitNodeId;

    public String getForbiddenId() {
        return forbiddenId;
    }

    public void setForbiddenId(String forbiddenId) {
        this.forbiddenId = forbiddenId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getEnterNodeId() {
        return enterNodeId;
    }

    public void setEnterNodeId(String enterNodeId) {
        this.enterNodeId = enterNodeId;
    }

    public String getExitNodeId() {
        return exitNodeId;
    }

    public void setExitNodeId(String exitNodeId) {
        this.exitNodeId = exitNodeId;
    }

    @Override
    public String toString() {
        return "ForbiddenTurn{" +
                "forbiddenId='" + forbiddenId + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", enterNodeId='" + enterNodeId + '\'' +
                ", exitNodeId='" + exitNodeId + '\'' +
                '}';
    }
}
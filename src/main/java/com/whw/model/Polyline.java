package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 折线
 */
@Entity
@Table(name = "polyline")
public class Polyline implements Serializable {

    /**
     * 折线编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "lineId", length = 32, columnDefinition = "char(32)")
    private String lineId;

    /**
     * 构成折线的各个节点points
     */
    @Column(name = "points", length = 500, columnDefinition = "varchar(255)")
    private String points;

    @Override
    public String toString() {
        return "Polyline{" +
                "lineId='" + lineId + '\'' +
                ", points='" + points + '\'' +
                '}';
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
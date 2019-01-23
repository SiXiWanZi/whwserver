package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 反馈
 */
@Entity
@Table(name = "feedback")
public class FeedBack implements Serializable {

    /**
     * 反馈编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "FKBH", length = 32,columnDefinition = "char(32)")
    private String fkbh;


    /**
     * 用户编号
     */
    @Column(name = "YHBH", length = 32,columnDefinition = "char(32)")
    private String yhbh;


    /**
     * 内容
     */
    @Column(name = "NR", length = 100,columnDefinition = "varchar(100)")
    private String nr;


    /**
     * 反馈时间
     */
    @Column(name = "FKSJ")
    private Date fksj;

    public String getFkbh() {
        return fkbh;
    }

    public void setFkbh(String fkbh) {
        this.fkbh = fkbh;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public Date getFksj() {
        return fksj;
    }

    public void setFksj(Date fksj) {
        this.fksj = fksj;
    }

    public FeedBack(String yhbh, String nr, Date fksj) {
        this.yhbh = yhbh;
        this.nr = nr;
        this.fksj = fksj;
    }

    public FeedBack() {
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "fkbh='" + fkbh + '\'' +
                ", yhbh='" + yhbh + '\'' +
                ", nr='" + nr + '\'' +
                ", fksj=" + fksj +
                '}';
    }
}
package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 收藏
 */
@Entity
@Table(name = "star")
public class Star implements Serializable {

    /**
     * 收藏编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "SCBH", length = 32,columnDefinition = "char(32)")
    private String scbh;

    /**
     * 用户编号
     */
    @Column(name = "YHBH", length = 32,columnDefinition = "char(32)")
    private String yhbh;

    /**
     * 物品编号
     */
    @Column(name = "WPBH", length = 32,columnDefinition = "char(32)")
    private String wpbh;

    /**
     * 集市编号
     */
    @Column(name = "JSBH", length = 32,columnDefinition = "char(32)")
    private String jsbh;

    /**
     * 收藏时间
     */
    @Column(name = "SCSJ")
    private Date scsj;

    public String getScbh() {
        return scbh;
    }

    public void setScbh(String scbh) {
        this.scbh = scbh;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public Date getScsj() {
        return scsj;
    }

    public void setScsj(Date scsj) {
        this.scsj = scsj;
    }

    public Star() {
    }

    public Star(String yhbh, String wpbh, String jsbh, Date scsj) {
        this.yhbh = yhbh;
        this.wpbh = wpbh;
        this.jsbh = jsbh;
        this.scsj = scsj;
    }

    @Override
    public String toString() {
        return "Star{" +
                "scbh='" + scbh + '\'' +
                ", yhbh='" + yhbh + '\'' +
                ", wpbh='" + wpbh + '\'' +
                ", jsbh='" + jsbh + '\'' +
                ", scsj=" + scsj +
                '}';
    }
}
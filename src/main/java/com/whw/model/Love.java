package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 点赞
 */
@Entity
@Table(name = "love")
public class Love implements Serializable {

    /**
     * 点赞编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "DZBH", length = 32,columnDefinition = "char(32)")
    private String dzbh;


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
     * 点赞时间
     */
    @Column(name = "DZSJ")
    private Date dzsj;

    public String getDzbh() {
        return dzbh;
    }

    public void setDzbh(String dzbh) {
        this.dzbh = dzbh;
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

    public Date getDzsj() {
        return dzsj;
    }

    public void setDzsj(Date dzsj) {
        this.dzsj = dzsj;
    }

    public Love() {
    }

    public Love(String yhbh, String wpbh, String jsbh, Date dzsj) {
        this.yhbh = yhbh;
        this.wpbh = wpbh;
        this.jsbh = jsbh;
        this.dzsj = dzsj;
    }

    @Override
    public String toString() {
        return "Love{" +
                "dzbh='" + dzbh + '\'' +
                ", yhbh='" + yhbh + '\'' +
                ", wpbh='" + wpbh + '\'' +
                ", jsbh='" + jsbh + '\'' +
                ", dzsj=" + dzsj +
                '}';
    }
}
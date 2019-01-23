package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 集市
 */
@Entity
@Table(name = "market")
public class Market implements Serializable {

    /**
     * 集市编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "JSBH", length = 32,columnDefinition = "char(32)")
    private String jsbh;


    /**
     * 用户编号
     */
    @Column(name = "YHBH", length = 32,columnDefinition = "char(32)")
    private String yhbh;

    /**
     * 主题
     */
    @Column(name = "ZT", length = 50,columnDefinition = "varchar(50)")
    private String zt;

    /**
     * 详情
     */
    @Column(name = "XQ",length=500,columnDefinition = "varchar(500)")
    private String xq;

    /**
     * 图片
     */
    @Column(name = "TP",length=500,columnDefinition = "varchar(500)")
    private String tp;

    /**
     *发帖时间
     */
    @Column(name = "FTSJ")
    private Date ftsj;

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public Date getFtsj() {
        return ftsj;
    }

    public void setFtsj(Date ftsj) {
        this.ftsj = ftsj;
    }

    public Market() {
    }

    public Market(String yhbh, String zt, String xq, String tp, Date ftsj) {
        this.yhbh = yhbh;
        this.zt = zt;
        this.xq = xq;
        this.tp = tp;
        this.ftsj = ftsj;
    }

    @Override
    public String toString() {
        return "Market{" +
                "jsbh='" + jsbh + '\'' +
                ", yhbh='" + yhbh + '\'' +
                ", zt='" + zt + '\'' +
                ", xq='" + xq + '\'' +
                ", tp='" + tp + '\'' +
                ", ftsj=" + ftsj +
                '}';
    }
}
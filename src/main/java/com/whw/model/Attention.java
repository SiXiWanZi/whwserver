package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 关注
 */
@Entity
@Table(name = "attention")
public class Attention implements Serializable {

    /**
     * 关注编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "GZBH", length = 32,columnDefinition = "char(32)")
    private String gzbh;


    /**
     * 用户编号
     */
    @Column(name = "YHBH", length = 32,columnDefinition = "char(32)")
    private String yhbh;


    /**
     * 被关注用户编号
     */
    @Column(name = "BGZYHBH", length = 32,columnDefinition = "char(32)")
    private String bgzyhbh;


    /**
     * 关注时间
     */
    @Column(name = "GZSJ")
    private Date gzsj;

    public String getGzbh() {
        return gzbh;
    }

    public void setGzbh(String gzbh) {
        this.gzbh = gzbh;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getBgzyhbh() {
        return bgzyhbh;
    }

    public void setBgzyhbh(String bgzyhbh) {
        this.bgzyhbh = bgzyhbh;
    }

    public Date getGzsj() {
        return gzsj;
    }

    public void setGzsj(Date gzsj) {
        this.gzsj = gzsj;
    }

    public Attention() {
    }

    public Attention(String yhbh, String bgzyhbh, Date gzsj) {
        this.yhbh = yhbh;
        this.bgzyhbh = bgzyhbh;
        this.gzsj = gzsj;
    }

    @Override
    public String toString() {
        return "Attention{" +
                "gzbh='" + gzbh + '\'' +
                ", yhbh='" + yhbh + '\'' +
                ", bgzyhbh='" + bgzyhbh + '\'' +
                ", gzsj=" + gzsj +
                '}';
    }
}
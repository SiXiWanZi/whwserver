package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 换物请求
 */
@Entity
@Table(name = "exchange_request")
public class ExchangeRequest implements Serializable {

    /**
     * 换物请求编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "HWQQBH", length = 32, columnDefinition = "char(32)")
    private String hwqqbh;


    /**
     * 用户编号
     */
    @Column(name = "YHBH", length = 32, columnDefinition = "char(32)")
    private String yhbh;

    /**
     * 想换物品编号
     */
    @Column(name = "XHWPBH", length = 32, columnDefinition = "char(32)")
    private String xhwpbh;

    /**
     * 我的物品编号
     */
    @Column(name = "WDWPBH", length = 32, columnDefinition = "char(32)")
    private String wdwpbh;


    /**
     * 选中时间
     */
    @Column(name = "XZSJ")
    private Date xzsj;

    /**
     * 备注
     */
    @Column(name = "BZ", length = 100, columnDefinition = "varchar(100)")
    private String bz;

    public String getHwqqbh() {
        return hwqqbh;
    }

    public void setHwqqbh(String hwqqbh) {
        this.hwqqbh = hwqqbh;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getXhwpbh() {
        return xhwpbh;
    }

    public void setXhwpbh(String xhwpbh) {
        this.xhwpbh = xhwpbh;
    }

    public Date getXzsj() {
        return xzsj;
    }

    public void setXzsj(Date xzsj) {
        this.xzsj = xzsj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public ExchangeRequest(String yhbh, String xhwpbh, Date xzsj, String bz) {
        this.yhbh = yhbh;
        this.xhwpbh = xhwpbh;
        this.xzsj = xzsj;
        this.bz = bz;
    }

    public ExchangeRequest() {
    }

    @Override
    public String toString() {
        return "ExchangeRequest{" +
                "hwqqbh='" + hwqqbh + '\'' +
                ", yhbh='" + yhbh + '\'' +
                ", xhwpbh='" + xhwpbh + '\'' +
                ", xzsj=" + xzsj +
                ", bz='" + bz + '\'' +
                '}';
    }
}
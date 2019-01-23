package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 物品类别
 */
@Entity
@Table(name = "goods_type")
public class GoodsType implements Serializable {

    /**
     * 类别编码
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")
    @Column(name = "LBBM", length = 6,columnDefinition = "char(6)")
    private String lbbm;

    /**
     * 父类编码
     */
   // @Column(name = "FLBM", length = 32,columnDefinition = "char(32)")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="FLBM",updatable = false)
    private GoodsType flbm;

    /**
     * 类别名称
     */
    @Column(name = "LBMC", length = 20,columnDefinition = "varchar(20)")
    private String lbmc;

    public String getLbbm() {
        return lbbm;
    }

    public void setLbbm(String lbbm) {
        this.lbbm = lbbm;
    }

    public GoodsType getFlbm() {
        return flbm;
    }

    public void setFlbm(GoodsType flbm) {
        this.flbm = flbm;
    }

    public String getLbmc() {
        return lbmc;
    }

    public void setLbmc(String lbmc) {
        this.lbmc = lbmc;
    }



    public GoodsType(String lbbm, GoodsType flbm, String lbmc) {
        this.lbbm = lbbm;
        this.flbm = flbm;
        this.lbmc = lbmc;
    }

    public GoodsType(GoodsType flbm, String lbmc) {
        this.flbm = flbm;
        this.lbmc = lbmc;
    }

    public GoodsType() {
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "lbbm='" + lbbm + '\'' +
                ", flbm=" + (flbm==null?"NULL":flbm) +
                ", lbmc='" + lbmc + '\'' +
                '}';
    }
}
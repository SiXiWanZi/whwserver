package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物品
 */
@Entity
@Table(name = "goods")
public class Goods implements Serializable {

    /**
     * 物品编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "WPBH", length = 32, columnDefinition = "char(32)")
    private String wpbh;


    /**
     * 用户编号
     */
    /*
    @Column(name = "YHBH", length = 32,columnDefinition = "char(32)")
    private String yhbh;
    */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "YHBH", updatable = false)
    private User user;

    /**
     * 类别编号
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LBBH")
    private GoodsType goodsType;


    /**
     * 标题
     */
    @Column(name = "BT", length = 50, columnDefinition = "varchar(50)")
    private String bt;

    /**
     * 新旧程度
     */
    @Column(name = "XJCD", length = 11)
    private int xjcd;


    /**
     * 描述
     */
    @Column(name = "MS", length = 400, columnDefinition = "varchar(400)")
    private String ms;


    /**
     * 想换物品
     */
    @Column(name = "XHWP", length = 100, columnDefinition = "varchar(100)")
    private String xhwp;

    /**
     * 估价
     */
    @Column(name = "GJ")
    private float gj;

    /**
     * 照片
     */
    @Column(name = "ZP", length = 500, columnDefinition = "varchar(500)")
    private String zp;

    /**
     * 所在省市
     */
    @Column(name = "SZSS", length = 30, columnDefinition = "varchar(30)")
    private String szss;

    /**
     * 交易类型
     */
    @Column(name = "JYLX", length = 1)
    private String jylx;


    /**
     * 交易状态
     */
    @Column(name = "JYZT", length = 1)
    private String jyzt;


    /**
     * 完结时间
     */
    @Column(name = "WJSJ")
    private Date wjsj;


    /**
     * 是否有效
     */
    @Column(name = "SFYX", length = 1)
    private String sfyx;

    /**
     * 发布时间
     */
    @Column(name = "FBSJ")
    private Date fbsj;

    /**
     * 点赞数量
     */
    @Column(name = "DZSL", length = 11, columnDefinition = "INT default 0")
    private int dzsl;

    /**
     * 评论数量
     */
    @Column(name = "PLSL", length = 11, columnDefinition = "INT default 0")
    private int plsl;

    /**
     * 想要数量
     */
    @Column(name = "XYSL", length = 11, columnDefinition = "INT default 0")
    private int xysl;

    /*@Column(name = "BZ",length = 300,columnDefinition = "varchar(300)")
    private String bz;*/

    public Goods(User user, GoodsType goodsType, String bt, int xjcd, String ms, String xhwp, float gj, String zp, String szss, String jylx, String jyzt, Date wjsj, String sfyx, Date fbsj, int dzsl, int plsl, int xysl) {
        this.user = user;
        this.goodsType = goodsType;
        this.bt = bt;
        this.xjcd = xjcd;
        this.ms = ms;
        this.xhwp = xhwp;
        this.gj = gj;
        this.zp = zp;
        this.szss = szss;
        this.jylx = jylx;
        this.jyzt = jyzt;
        this.wjsj = wjsj;
        this.sfyx = sfyx;
        this.fbsj = fbsj;
        this.dzsl = dzsl;
        this.plsl = plsl;
        this.xysl = xysl;
    }

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public int getXjcd() {
        return xjcd;
    }

    public void setXjcd(int xjcd) {
        this.xjcd = xjcd;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getXhwp() {
        return xhwp;
    }

    public void setXhwp(String xhwp) {
        this.xhwp = xhwp;
    }

    public float getGj() {
        return gj;
    }

    public void setGj(float gj) {
        this.gj = gj;
    }

    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }

    public String getSzss() {
        return szss;
    }

    public void setSzss(String szss) {
        this.szss = szss;
    }

    public String getJylx() {
        return jylx;
    }

    public void setJylx(String jylx) {
        this.jylx = jylx;
    }

    public String getJyzt() {
        return jyzt;
    }

    public void setJyzt(String jyzt) {
        this.jyzt = jyzt;
    }

    public Date getWjsj() {
        return wjsj;
    }

    public void setWjsj(Date wjsj) {
        this.wjsj = wjsj;
    }

    public String getSfyx() {
        return sfyx;
    }

    public void setSfyx(String sfyx) {
        this.sfyx = sfyx;
    }

    public Date getFbsj() {
        return fbsj;
    }

    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }

    public int getDzsl() {
        return dzsl;
    }

    public void setDzsl(int dzsl) {
        this.dzsl = dzsl;
    }

    public int getPlsl() {
        return plsl;
    }

    public void setPlsl(int plsl) {
        this.plsl = plsl;
    }

    public int getXysl() {
        return xysl;
    }

    public void setXysl(int xysl) {
        this.xysl = xysl;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "wpbh='" + wpbh + '\'' +
                ", user=" + (user==null?"NULL":user) +
                ", goodsType=" + (goodsType==null?"NULL":goodsType) +
                ", bt='" + bt + '\'' +
                ", xjcd=" + xjcd +
                ", ms='" + ms + '\'' +
                ", xhwp='" + xhwp + '\'' +
                ", gj=" + gj +
                ", zp='" + zp + '\'' +
                ", szss='" + szss + '\'' +
                ", jylx='" + jylx + '\'' +
                ", jyzt='" + jyzt + '\'' +
                ", wjsj=" + wjsj +
                ", sfyx='" + sfyx + '\'' +
                ", fbsj=" + fbsj +
                ", dzsl=" + dzsl +
                ", plsl=" + plsl +
                ", xysl=" + xysl +
                '}';
    }
}
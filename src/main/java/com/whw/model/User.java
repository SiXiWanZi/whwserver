package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * 用户编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "YHBH", length = 32,columnDefinition = "char(32)")
    private String yhbh;

    /**
     * 昵称
     */
    @Column(name = "NC", length = 15,columnDefinition = "varchar(15)")
    private String nc;

    /**
     * 密码
     */
    @Column(name = "MM", length = 20,columnDefinition = "varchar(20)")
    private String mm;

    /**
     * 用户头像
     */
    @Column(name = "YHTX", length = 50,columnDefinition = "varchar(50)")
    private String yhtx;

    /**
     * 用户地址
     */
    @Column(name = "YHDZ", length = 200,columnDefinition = "varchar(200)")
    private String yhdz;

    /**
     * 联系电话
     */
    @Column(name = "LXDH", length = 11,columnDefinition = "char(11)")
    private String lxdh;

    /**
     * 邮箱
     */
    @Column(name = "YX", length = 50,columnDefinition = "varchar(50)")
    private String yx;

    /**
     * 信用度
     */
    @Column(name = "XYD", length = 11)
    private int xyd;

    /**
     * 注册时间
     */
    @Column(name="ZCSJ")
    private Date zcsj;

    /**
     * 性别
     */
    @Column(name="XB",length = 1,columnDefinition = "char(1)")
    private char xb;

    /**
     * 出生年月
     */
    @Column(name="CSNY")
    private Date csny;

    /**
     * 个性签名
     */
    @Column(name="GXQM",length=30,columnDefinition = "varchar(30)")
    private String gxqm;

    /**
     * token
     */
    @Column(name="token",length=200,columnDefinition = "varchar(200)")
    private String token;

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYhtx() {
        return yhtx;
    }

    public void setYhtx(String yhtx) {
        this.yhtx = yhtx;
    }

    public String getYhdz() {
        return yhdz;
    }

    public void setYhdz(String yhdz) {
        this.yhdz = yhdz;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public int getXyd() {
        return xyd;
    }

    public void setXyd(int xyd) {
        this.xyd = xyd;
    }

    public Date getZcsj() {
        return zcsj;
    }

    public void setZcsj(Date zcsj) {
        this.zcsj = zcsj;
    }

    public char getXb() {
        return xb;
    }

    public void setXb(char xb) {
        this.xb = xb;
    }

    public Date getCsny() {
        return csny;
    }

    public void setCsny(Date csny) {
        this.csny = csny;
    }

    public String getGxqm() {
        return gxqm;
    }

    public void setGxqm(String gxqm) {
        this.gxqm = gxqm;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User() {
    }

    public User(String nc, String mm, String yhtx, String yhdz, String lxdh, String yx, int xyd, Date zcsj, char xb, Date csny, String gxqm, String token) {
        this.nc = nc;
        this.mm = mm;
        this.yhtx = yhtx;
        this.yhdz = yhdz;
        this.lxdh = lxdh;
        this.yx = yx;
        this.xyd = xyd;
        this.zcsj = zcsj;
        this.xb = xb;
        this.csny = csny;
        this.gxqm = gxqm;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "yhbh='" + yhbh + '\'' +
                ", nc='" + nc + '\'' +
                ", mm='" + mm + '\'' +
                ", yhtx='" + yhtx + '\'' +
                ", yhdz='" + yhdz + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", yx='" + yx + '\'' +
                ", xyd=" + xyd +
                ", zcsj=" + zcsj +
                ", xb=" + xb +
                ", csny=" + csny +
                ", gxqm='" + gxqm + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
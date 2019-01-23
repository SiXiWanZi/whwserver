package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    /**
     * 评论编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "PLBH", length = 32,columnDefinition = "char(32)")
    private String plbh;


    /**
     * 用户编号
     */
//    /**
//     * 评论用户
//     */
//    @Column(name = "YHBH", length = 32,columnDefinition = "char(32)")
//    private String yhbh;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "YHBH",updatable = false)
    private User plyh;

    /**
     *物品编号
     */
    @Column(name = "WPBH", length = 32,columnDefinition = "char(32)")
    private String wpbh;


    /**
     * 集市编号
     */
    @Column(name = "JSBH", length = 32,columnDefinition = "char(32)")
    private String jsbh;
//
//    /**
//     * 上级评论编号
//     */
//    @Column(name = "SJPLBH", length = 32,columnDefinition = "char(32)")
//    private String sjplbh;

    /**
     * 下级评论
     */

    @OneToMany(mappedBy = "comment",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> childComments = new ArrayList<>();

    /**
     * 上级评论编号
     */
    @ManyToOne(cascade=CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name="SJPLBH",referencedColumnName = "plbh",updatable = false)
    private Comment comment;

    /**
     * 评论内容
     */
    @Column(name = "PLNR", length = 500,columnDefinition = "varchar(500)")
    private String plnr;


    /**
     * 评论时间
     */
    @Column(name = "plsj")
    private Date plsj;


    public String getPlbh() {
        return plbh;
    }

    public void setPlbh(String plbh) {
        this.plbh = plbh;
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

    public User getPlyh() {
        return plyh;
    }

    public void setPlyh(User plyh) {
        this.plyh = plyh;
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }

    public String getPlnr() {
        return plnr;
    }

    public void setPlnr(String plnr) {
        this.plnr = plnr;
    }

    public Date getPlsj() {
        return plsj;
    }

    public void setPlsj(Date plsj) {
        this.plsj = plsj;
    }


    public Comment(User plyh, String wpbh, String jsbh, List<Comment> childComments, Comment comment, String plnr, Date plsj) {
        this.plyh = plyh;
        this.wpbh = wpbh;
        this.jsbh = jsbh;
        this.childComments = childComments;
        this.comment = comment;
        this.plnr = plnr;
        this.plsj = plsj;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "plbh='" + plbh + '\'' +
                ", plyh=" + plyh +
                ", wpbh='" + wpbh + '\'' +
                ", jsbh='" + jsbh + '\'' +
                ", childComments=" + (childComments==null?"null":childComments)+
                //", comment=" + (comment==null?"null":comment)+
                ", plnr='" + plnr + '\'' +
                ", plsj=" + plsj +
                '}';
    }
}
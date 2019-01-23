package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 */
@Entity
@Table(name = "operation_log")
public class OperationLog implements Serializable {
    /**
     * 日志编号
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "RZBH", length = 32,columnDefinition = "char(32)")
    private String rzbh;

    /**
     * 日志类型
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="rzlx",insertable = false,updatable = false)
    private LogType rzlx;

    /**
     * 日志内容
     */
    @Column(name = "RZNR", length = 300,columnDefinition = "varchar(300)")
    private String rznr;

    /**
     * 操作时间
     */
    @Column(name = "CZSJ")
    private Date czsj;

    public OperationLog(LogType rzlx, String rznr, Date czsj) {
        this.rzlx = rzlx;
        this.rznr = rznr;
        this.czsj = czsj;
    }

    public String getRzbh() {
        return rzbh;
    }

    public void setRzbh(String rzbh) {
        this.rzbh = rzbh;
    }

    public LogType getRzlx() {
        return rzlx;
    }

    public void setRzlx(LogType rzlx) {
        this.rzlx = rzlx;
    }

    public String getRznr() {
        return rznr;
    }

    public void setRznr(String rznr) {
        this.rznr = rznr;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public OperationLog() {
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "rzbh='" + rzbh + '\'' +
                ", rzlx=" + rzlx +
                ", rznr='" + rznr + '\'' +
                ", czsj=" + czsj +
                '}';
    }
}
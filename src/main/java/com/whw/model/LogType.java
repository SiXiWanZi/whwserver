package com.whw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 日志类型
 */
@Entity
@Table(name = "log_type")
public class LogType implements Serializable {

    /**
     * 日志类型
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")
    @Column(name = "RZLX", length = 2,columnDefinition = "char(2)")
    private String rzlx;


    /**
     * 日志类型名称
     */
    @Column(name = "RZLXMC", length = 40,columnDefinition = "varchar(40)")
    private String rzlxmc;


    public String getRzlxmc() {
        return rzlxmc;
    }

    public void setRzlxmc(String rzlxmc) {
        this.rzlxmc = rzlxmc;
    }

    public String getRzlx() {
        return rzlx;
    }

    public void setRzlx(String rzlx) {
        this.rzlx = rzlx;
    }

    public LogType(String rzlx, String rzlxmc) {
        this.rzlx = rzlx;
        this.rzlxmc = rzlxmc;
    }

    public LogType() {
    }

    @Override
    public String toString() {
        return "LogType{" +
                "rzlx='" + rzlx + '\'' +
                ", rzlxmc='" + rzlxmc + '\'' +
                '}';
    }
}
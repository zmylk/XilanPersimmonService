package com.xilan.begin.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 22:04
 */
@Data
@Entity
public class TimeOpe {
    private static final long serialVersionUID = 1L;
    /**
     * uuid
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private String openId;
    /**
     * startime
     */
    private Date startime;
    /**
     * endtime
     */
    private Date endtime;
    /**
     * year
     */
    private Integer year;
    /**
     * 月
     */
    private Integer month;


    /**
     * 日
     */
    private Integer day;
    /**
     * 时
     */
    private Integer hour;
    /**
     * 学习时长
     */
    private Integer evaluation1;
    /**
     * 评分
     */
    private Integer evaluation2;
    /**
     * 事项
     */
    private String evaluation3;
    /**
     * 评价5
     */
    private Integer evaluation4;
    /**
     * 评价5
     */
    private Integer evaluation5;
    /**
     * 一个月第几周
     */
    private Integer week;
    /**
     * 一周中第几天
     */
    private Integer dayofweek;

}

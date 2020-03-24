package com.xilan.bengin.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
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
     * 评价5
     */
    private Integer evaluation1;
    /**
     * 评价5
     */
    private Integer evaluation2;
    /**
     * 评价5
     */
    private Integer evaluation3;
    /**
     * 评价5
     */
    private Integer evaluation4;
    /**
     * 评价5
     */
    private Integer evaluation5;

}

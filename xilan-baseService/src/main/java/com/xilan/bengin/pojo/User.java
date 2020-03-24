package com.xilan.bengin.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * open_id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private String openId;
    /**
     * skey
     */
    private String skey;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastVisitTime;
    /**
     * session_key
     */
    private String sessionKey;
    /**
     * 市
     */
    private String city;
    /**
     * 省
     */
    private String province;
    /**
     * 国
     */
    private String country;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 网名
     */
    private String nickName;
}

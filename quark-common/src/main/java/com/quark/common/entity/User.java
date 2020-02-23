package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import com.quark.common.utils.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="quark_user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //注册邮箱
    @Column(nullable = false)
    private String email;

    // 用户名
    @Column(unique = true, nullable = false)
    private String username;

    // 密码
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    // 头像
    private String icon ="http://127.0.0.1:9999/group1/M00/00/00/L2psHl44LneAEgHAAAARSHirAps496.tmp";

    // 个人签名
    private String signature;

    // 注册时间
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = "GMT+8")
    private Date initTime;

    //性别 0 ：男 1：女
    private Integer sex = 0;

    //是否被封禁,默认为１：开启
    @Column(nullable = false)
    private Integer enable = 1;

}

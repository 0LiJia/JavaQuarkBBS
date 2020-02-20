package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quark.common.utils.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 回复
 */
@Entity
@Table(name = "quark_reply")
@Data
public class Reply implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //回复的内容
    @Column(columnDefinition = "text", nullable = false)
    private String content;

    //回复时间
    @JsonFormat(pattern = Constants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date initTime;

    //点赞个数
    private Integer up = 0;

    //与话题的关联关系
    @ManyToOne
    @JoinColumn(nullable = false, name = "posts_id")
    @JsonIgnore
    private Posts posts;

    //与用户的关联关系
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}

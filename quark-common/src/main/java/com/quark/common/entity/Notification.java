package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quark.common.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * 通知
 */
@Entity
@Table(name = "quark_notification")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue
    private Integer id;

    //通知是否已读
    @Column(name = "is_read")
    private boolean isRead = false;

    //要通知的用户：立即加载
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "touser_id")
    private User touser;

    //发起通知的用户
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "fromuser_id")
    private User fromuser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "posts_id")
    private Posts posts;

    //发布时间
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date initTime;

}

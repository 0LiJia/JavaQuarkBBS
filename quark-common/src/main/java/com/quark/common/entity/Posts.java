package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quark.common.utils.Constants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 帖子
 */
@Entity
@Table(name = "quark_posts")
@Getter
@Setter
public class Posts implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //与标签的关系
    @ManyToOne
    @JoinColumn(nullable = false, name = "label_id")
    private Label label;

    //标题
    @Column(unique = true, nullable = false)
    private String title;

    //内容
    @Column(columnDefinition = "text")
    private String content;

    //发布时间
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date initTime;

    //是否置顶
    private boolean top;

    //是否精华
    private boolean good;

    //与用户的关联关系
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;


    //回复数量
    @Column(name = "reply_count")
    private int replyCount = 0;

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", label=" + label +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", initTime=" + initTime +
                ", top=" + top +
                ", good=" + good +
                ", user=" + user +
                ", replyCount=" + replyCount +
                '}';
    }
}

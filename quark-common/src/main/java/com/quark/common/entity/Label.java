package com.quark.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 标签
 */
@Entity
@Table(name = "quark_label")
@Data
public class Label implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //标签名称
    @Column(nullable = false, unique = true)
    private String name;

    //主题数量
    @Column(name = "posts_count")
    private Integer postsCount = 0;

    //详情
    private String details;


}

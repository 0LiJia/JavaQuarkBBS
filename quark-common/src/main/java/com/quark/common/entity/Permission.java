package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quark_permission")
@Getter
@Setter
public class Permission implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String perurl;

    //资源类型　　1：菜单　2：按钮
    private Integer type;

    //父权限
    @Column(nullable = false)
    private Integer parentid;

    //排序
    private Integer sort;

    //是否选中
    @Transient
    private String checked;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

}

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
@Table(name = "quark_role")
@Getter
@Setter
public class Role implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true,nullable = false)
    private String name;

    //角色描述
    private String description;

    //是否持有角色标志
    @Transient
    private Integer selected;

    //角色与用户的关联关系
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<AdminUser> adminUsers = new HashSet<>();

    //角色与权限的关联关系
    @JsonIgnore
    @JoinTable(name = "quark_role_permission",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permissions_id",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission> permissions = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", selected=" + selected +
                '}';
    }

}

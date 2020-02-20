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
@Table(name = "quark_adminuser")
@Getter
@Setter
@ToString
public class AdminUser implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;


    //是否可以使用,默认为１
    @Column(nullable = false)
    private Integer enable = 1;

    @JsonIgnore
    @JoinTable(name = "quark_adminuser_role",
            joinColumns = {@JoinColumn(name = "adminuser_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "AdminUser{" +
                "Id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable;
    }
}

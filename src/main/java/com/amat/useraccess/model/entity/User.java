package com.amat.useraccess.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_user")
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

//    /**
//     * User id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id")
//    private Integer id;

    /**
     * Login name which login need to be used
     */
    @Column(name = "login_name", columnDefinition = "varchar(50) not null")
    private String loginName;

    /**
     * User first name
     */
    @Column(name = "first_name", columnDefinition = "varchar(50) not null")
    private String firstName;

    /**
     * User last name
     */
    @Column(name = "last_name", columnDefinition = "varchar(50) not null")
    private String lastName;

    /**
     * User email
     */
    @Column(name = "email", columnDefinition = "varchar(255) not null")
    private String email;

    /**
     * User password
     */
    @Column(name = "password", columnDefinition = "varchar(50) not null")
    private String password;

    /**
     * User description
     */
    @Column(name = "description", columnDefinition = "varchar(1023) not null")
    private String description;

    /**
     * User who edit this account
     */
    @Column(name = "user_name_edit", columnDefinition = "varchar(50) not null")
    private String userNameEdit;

    /**
     * User password expire time
     */
    @Column(name = "expire_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;

    /**
     * User need to change password or not when first login
     */
    @Column(name = "need_change_password", columnDefinition = "varchar(1) not null")
    private String needChangePassword;
}

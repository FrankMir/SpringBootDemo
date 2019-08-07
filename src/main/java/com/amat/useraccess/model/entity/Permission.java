package com.amat.useraccess.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_permission")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {

//    /**
//     *  Permission id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id")
//    private Integer id;

    /**
     * Permission object
     */
    @Column(name = "object", columnDefinition = "varchar(255) not null")
    private String object;

    /**
     * Permission symbol
     */
    @Column(name = "symbol", columnDefinition = "varchar(255) not null")
    private String symbol;

    /**
     * Permission description
     */
    @Column(name = "description", columnDefinition = "varchar(1023) not null")
    private String description;

    /**
     * Permission create user
     */
    @Column(name = "create_user", columnDefinition = "varchar(50) not null")
    private String createUser;

    /**
     * Permission update user
     */
    @Column(name = "update_user", columnDefinition = "varchar(50) not null")
    private String updateUser;
}

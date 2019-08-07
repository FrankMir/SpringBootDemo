package com.amat.useraccess.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_group")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseEntity {

//    /**
//     * Group id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id")
//    private Integer id;

    /**
     * Group name
     */
    @Column(name = "group_name", columnDefinition = "varchar(50) not null")
    private String groupName;

    /**
     * Group description
     */
    @Column(name = "description", columnDefinition = "varchar(1023) default ''")
    private String description;

    /**
     * Group create user
     */
    @Column(name = "create_user", columnDefinition = "varchar(50) not null")
    private String createUser;

    /**
     * Group update user
     */
    @Column(name = "update_user", columnDefinition = "varchar(50) not null")
    private String updateUser;
}

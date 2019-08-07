package com.amat.useraccess.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@ToString
@Table(name = "t_policy")
@EqualsAndHashCode(callSuper = true)
public class Policy extends BaseEntity{

    @Column(name = "policy_name", columnDefinition = "varchar(50) not null")
    private String policyName;

    @Column(name = "description", columnDefinition = "varchar(50) default ''")
    private String description;

    @Column(name = "expire_day_count", columnDefinition = "NUMBER default -1")
    private String expireDayCount;

    @Column(name = "check_new_password", columnDefinition = "varchar(1) not null")
    private String checkNewPassword;

}

package com.amat.useraccess.model.entity;

import com.amat.useraccess.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Base Entity
 *
 * @author xs
 * @date 7/29/19
 */
@Data
@ToString
@MappedSuperclass
@EqualsAndHashCode
public class BaseEntity {

    /**
     * Object Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    /**
     * Create time
     */
    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * Update time
     */
    @Column(name = "update_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * Delete flag
     */
    @Column(name = "deleted", columnDefinition = "NUMBER default 0")
    private Boolean deleted = false;

    /**
     * Save 之前执行
     */
    @PrePersist
    protected void prePersist() {
        deleted = false;
        Date now = DateUtils.now();
        if(createTime == null) {
            createTime = now;
        }

        if(updateTime == null) {
            updateTime = now;
        }
    }

    /**
     * Update 之前执行
     */
    @PreUpdate
    protected void preUpdate() {
        updateTime = new Date();
    }

    /**
     * Remove 之前执行
     */
    @PreRemove
    protected void preRemove() {
        updateTime = new Date();
    }
}

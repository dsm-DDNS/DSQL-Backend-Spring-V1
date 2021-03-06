package com.ddns.dsqlbackendspringv1.global.base.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "created_date")
    open var createdDate: LocalDateTime? = null
        protected set

    @LastModifiedDate
    @Column(name = "update_date")
    open var updateDate: LocalDateTime? = null
        protected set


}
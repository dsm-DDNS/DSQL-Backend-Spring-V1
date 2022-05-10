package com.ddns.dsqlbackendspringv1.global.base.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {

    @CreatedDate
    val createdDate: LocalDateTime? = null

    @LastModifiedDate
    var updateDate: LocalDateTime? = null

    fun getCreateDate(): LocalDateTime {
        return createdDate!!
    }


}
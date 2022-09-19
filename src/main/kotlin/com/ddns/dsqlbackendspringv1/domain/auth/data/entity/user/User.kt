package com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user

import com.ddns.dsqlbackendspringv1.domain.auth.business.dto.FullUserDto
import com.ddns.dsqlbackendspringv1.domain.auth.business.dto.MiniUserDto
import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.type.Role
import com.ddns.dsqlbackendspringv1.global.base.entity.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.Entity
import javax.persistence.Id


@Entity
@DiscriminatorColumn(name = "user_type")
sealed class User(
    id: String,
    role: Role,
    name: String,
    email: String,
    pw: String,
    introduction: String
): BaseTimeEntity() {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id = id

    @Column(name = "user_role")
    var role: Role = role
        protected set
    @Column(name = "user_name")
    var name: String = name
        protected set
    @Column(name = "user_email")
    val email: String = email

    @Column(name = "user_password")
    var pw: String = pw
        protected set
    @Column(name = "user_introduction")
    var introduction: String = introduction
        protected set

    fun toMiniUserDto(): MiniUserDto {
        return MiniUserDto(
            this.id,
            this.name,
            this.email
        )
    }

    fun toFullUserDto(): FullUserDto {
        return FullUserDto(
            this.id,
            this.role,
            this.name,
            this.email,
            this.introduction
        )
    }

    override fun toString(): String {
        return "${this.id}, ${this.email}, ${this.name}, ${this.role}, ${this.introduction}, ${this.createdDate}, ${this.updateDate}"
    }

}
package com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.type.Role
import com.ddns.dsqlbackendspringv1.global.base.entity.BaseTimeEntity
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
    val id = id

    var role: Role = role
        private set
    var name: String = name
        private set
    val email: String = email

    var pw: String = pw
        private set
    var introduction: String = introduction
        private set

}
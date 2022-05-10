package com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.type.Role
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity


@Entity
@DiscriminatorValue("teacher")
class Teacher(
    id: String,
    name: String,
    email: String,
    pw: String,
    introduction: String
): User(
    id,
    Role.TEACHER,
    name,
    email,
    pw,
    introduction
) {

}
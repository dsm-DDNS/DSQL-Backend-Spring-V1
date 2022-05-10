package com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.type.Role
import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("admin")
class Admin(
    id: String,
    name: String,
    email: String,
    pw: String,
    introduction: String
): User(
    id,
    Role.ADMIN,
    name,
    email,
    pw,
    introduction
) {
}
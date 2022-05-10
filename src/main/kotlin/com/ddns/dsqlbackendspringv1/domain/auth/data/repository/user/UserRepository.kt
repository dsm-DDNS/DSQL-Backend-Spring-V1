package com.ddns.dsqlbackendspringv1.domain.auth.data.repository.user

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {

}
package com.ddns.dsqlbackendspringv1.domain.auth.data.repository.user

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, String> {

    fun findByEmail(email: String): Optional<User>

}
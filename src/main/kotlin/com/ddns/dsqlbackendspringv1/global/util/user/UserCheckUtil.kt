package com.ddns.dsqlbackendspringv1.global.util.user

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.auth.data.repository.user.UserRepository
import com.ddns.dsqlbackendspringv1.domain.auth.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class UserCheckUtil(
    private val userRepository: UserRepository
) {

    fun getCurrentSubject(): String {
        return SecurityContextHolder.getContext().authentication.credentials.toString()
    }

    fun getCurrentUser(): User {
        val subject = getCurrentSubject()
        return userRepository.findById(subject).orElse(null)?: throw UserNotFoundException(subject)
    }

}
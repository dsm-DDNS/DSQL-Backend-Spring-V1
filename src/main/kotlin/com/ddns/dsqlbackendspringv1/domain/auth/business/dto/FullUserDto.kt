package com.ddns.dsqlbackendspringv1.domain.auth.business.dto

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.type.Role

data class FullUserDto(
    val id: String,
    val role: Role,
    val name: String,
    val email: String,
    val introduction: String
)

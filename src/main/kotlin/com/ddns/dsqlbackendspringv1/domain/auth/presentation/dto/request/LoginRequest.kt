package com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request

data class LoginRequest (
    val email: String,
    val pw: String
)
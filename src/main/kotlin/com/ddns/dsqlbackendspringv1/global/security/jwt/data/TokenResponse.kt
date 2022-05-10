package com.ddns.dsqlbackendspringv1.global.security.jwt.data

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)

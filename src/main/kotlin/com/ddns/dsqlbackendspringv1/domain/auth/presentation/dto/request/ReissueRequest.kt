package com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request

data class ReissueRequest(
    val accessToken: String,
    val refreshToken: String

)

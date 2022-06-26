package com.ddns.dsqlbackendspringv1.domain.auth.business.service

import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.LoginRequest
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.ReissueRequest
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.SignupRequest
import com.ddns.dsqlbackendspringv1.global.security.jwt.data.TokenResponse

interface AuthService {

    fun signup(request: SignupRequest): TokenResponse
    fun login(request: LoginRequest): TokenResponse
    fun reissue(request: ReissueRequest): TokenResponse


}
package com.ddns.dsqlbackendspringv1.domain.auth.presentation.controller

import com.ddns.dsqlbackendspringv1.domain.auth.business.service.AuthService
import com.ddns.dsqlbackendspringv1.domain.auth.business.service.Credentials
import com.ddns.dsqlbackendspringv1.domain.auth.business.service.EmailService
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.LoginRequest
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.ReissueRequest
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.SignupRequest
import com.ddns.dsqlbackendspringv1.global.security.jwt.data.TokenResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.CurrentSecurityContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/dsql/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val emailService: EmailService
) {

    @PostMapping("/email")
    fun checkEmail(@RequestParam email: String) {
        emailService.sendCodeToEmail(email)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): TokenResponse {
        return authService.signup(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): TokenResponse {
        return authService.login(request)
    }

    @PutMapping("/reissue")
    fun reissue(@RequestBody request: ReissueRequest): TokenResponse{
        return authService.reissue(request)
    }

}
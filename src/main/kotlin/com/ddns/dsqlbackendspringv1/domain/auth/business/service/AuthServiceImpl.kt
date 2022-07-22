package com.ddns.dsqlbackendspringv1.domain.auth.business.service

import com.ddns.dsqlbackendspringv1.domain.auth.exception.EmailCheckCodeException
import com.ddns.dsqlbackendspringv1.domain.auth.exception.IncorrectPasswordException
import com.ddns.dsqlbackendspringv1.domain.auth.exception.UserNotFoundException
import com.ddns.dsqlbackendspringv1.domain.auth.business.service.emailCheck.PermitEmailListProperty
import com.ddns.dsqlbackendspringv1.domain.auth.data.token.RefreshToken
import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.type.Role
import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.Admin
import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.Teacher
import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.auth.data.repository.token.EmailCheckCodeRepository
import com.ddns.dsqlbackendspringv1.domain.auth.data.repository.token.RefreshTokenRepository
import com.ddns.dsqlbackendspringv1.domain.auth.data.repository.user.UserRepository
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.LoginRequest
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.ReissueRequest
import com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request.SignupRequest
import com.ddns.dsqlbackendspringv1.global.security.jwt.TokenProvider
import com.ddns.dsqlbackendspringv1.global.security.jwt.data.TokenResponse
import com.ddns.dsqlbackendspringv1.global.security.jwt.exception.ExpiredTokenException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class AuthServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val emailCheckCodeRepository: EmailCheckCodeRepository,
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository

): AuthService {

    override fun signup(request: SignupRequest): TokenResponse {
        if (
            true
//            (emailCheckCodeRepository.findById(request.email).orElse(null)?: throw EmailCheckCodeException(request.email)
//        ).code == request.emailCheckCode
        ) {
            val encPw = passwordEncoder.encode(request.pw)
            val user = if (request.userType.equals(Role.ADMIN))
                Admin(
                    UUID.randomUUID().toString(),
                    request.name,
                    request.email,
                    encPw,
                    request.introduction
                )
            else Teacher(
                UUID.randomUUID().toString(),
                request.name,
                request.email,
                encPw,
                request.introduction
            )

            userRepository.save(
                user
            )

            return tokenProvider.encode(user.id)
        } else throw EmailCheckCodeException(request.emailCheckCode)
    }

    override fun login(request: LoginRequest): TokenResponse {
        val user = userRepository.findByEmail(request.email)
            .orElse(null)?: throw UserNotFoundException(request.email)
        if (passwordEncoder.matches(request.pw, user.pw)) {
            return tokenProvider.encode(user.id)
        } else throw IncorrectPasswordException(request.pw)
    }

    override fun reissue(request: ReissueRequest): TokenResponse {
        if (tokenProvider.isExpired(request.refreshToken)) throw ExpiredTokenException(request.refreshToken)

        val userId = tokenProvider.decodeBody(request.accessToken).subject
        val user = userRepository.findById(userId).orElse(null)?: throw UserNotFoundException(userId)
        val tokenResponse = tokenProvider.encode(user.id)
        val token = RefreshToken(user.id, tokenResponse.refreshToken)

        refreshTokenRepository.findById(user.id).map {
            it.reset(token.token)
        }.orElse(null)?: refreshTokenRepository.save(token)

        return tokenResponse
    }

    override fun test(@Credentials email: String?): String {
        return email?: "null"
    }


}
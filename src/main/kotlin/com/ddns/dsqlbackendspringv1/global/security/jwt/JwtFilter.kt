package com.ddns.dsqlbackendspringv1.global.security.jwt

import com.ddns.dsqlbackendspringv1.global.security.jwt.auth.CustomAuthDetails
import com.ddns.dsqlbackendspringv1.global.security.jwt.auth.CustomAuthDetailsService
import com.ddns.dsqlbackendspringv1.global.security.jwt.exception.InvalidTokenException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtFilter(
    private val tokenProvider: TokenProvider,
    private val customAuthDetailsService: CustomAuthDetailsService
): OncePerRequestFilter() {

    companion object{
        const val AUTH = "Authorization"
    }
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = getToken(request)
        val subject: String = tokenProvider.getSubjectWithExpiredCheck(token)
        val userDetails = customAuthDetailsService.loadUserByUsername(subject)
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userDetails, subject, userDetails.authorities)

        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(AUTH) ?: throw InvalidTokenException(AUTH)
        if (bearerToken.startsWith("Bearer ")) return bearerToken.substring(7)
        throw InvalidTokenException(bearerToken)
    }
}
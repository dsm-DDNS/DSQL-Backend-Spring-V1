package com.ddns.dsqlbackendspringv1.global.security.config

import com.ddns.dsqlbackendspringv1.global.security.jwt.JwtFilter
import com.ddns.dsqlbackendspringv1.global.security.jwt.TokenProvider
import com.ddns.dsqlbackendspringv1.global.security.jwt.auth.CustomAuthDetailsService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsUtils


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val tokenProvider: TokenProvider,
    private val customAuthDetailsService: CustomAuthDetailsService,
    private val objectMapper: ObjectMapper
): WebSecurityConfigurerAdapter() {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**", "/api-docs.json"
        , "/swagger-ui.html", "/dsql-api-docs/**"
        )
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .csrf().disable()
            .formLogin().disable()
            .cors()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers(
                "/api/**").permitAll()
            .antMatchers("/api/dsql/v1/news/img").authenticated()
            .antMatchers("/api/dsql/v1/auth/reissue").authenticated()
            .antMatchers("/api/dsql/v1/project/img").authenticated()
            .antMatchers("/api/dsql/v1/project/dev").authenticated()
            .antMatchers("/api/dsql/v1/project/url").authenticated()
            .anyRequest().permitAll()
            .and()
            .apply(FilterConfiguration(tokenProvider, customAuthDetailsService, objectMapper))
    }


}
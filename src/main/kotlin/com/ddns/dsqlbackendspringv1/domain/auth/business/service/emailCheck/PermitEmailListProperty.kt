package com.ddns.dsqlbackendspringv1.domain.auth.business.service.emailCheck

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("auth.permit")
@ConstructorBinding
data class PermitEmailListProperty (
    val email : List<String>,
)

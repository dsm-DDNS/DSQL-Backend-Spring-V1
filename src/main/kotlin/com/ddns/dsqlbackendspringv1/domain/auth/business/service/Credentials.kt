package com.ddns.dsqlbackendspringv1.domain.auth.business.service

import org.springframework.security.core.annotation.CurrentSecurityContext

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.LOCAL_VARIABLE)
@Retention(AnnotationRetention.RUNTIME)
@CurrentSecurityContext(expression = "authentication.credentials")
annotation class Credentials()

package com.ddns.dsqlbackendspringv1.domain.auth.business.service

interface EmailService {

    fun sendCodeToEmail(email: String)

}
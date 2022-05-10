package com.ddns.dsqlbackendspringv1.infra.alarm.mail.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl


@Configuration
class MailConfiguration {

    @Bean
    fun mailSender(): JavaMailSender {
        return JavaMailSenderImpl()
    }

}
package com.ddns.dsqlbackendspringv1.infra.alarm.mail

import com.ddns.dsqlbackendspringv1.infra.alarm.AlarmService
import org.springframework.stereotype.Service

interface MailService {

    fun sendHtmlMail(to: String, title: String, templatePath: String, models: Map<String, Any>)


}
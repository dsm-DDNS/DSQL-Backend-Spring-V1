package com.ddns.dsqlbackendspringv1.infra.alarm.mail

import com.ddns.dsqlbackendspringv1.infra.alarm.mail.exception.MailSendingException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import javax.mail.Authenticator
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.MimeMessage


@Service
class MailServiceImpl(
    private val jms: JavaMailSender,
    private val templateEngine: TemplateEngine
): MailService {


    override fun sendHtmlMail(to: String, title: String, templatePath: String, models: Map<String, Any>) {
        val message: MimeMessage = jms.createMimeMessage()
        var context = Context()
        models.forEach(context::setVariable)
        val content: String = templateEngine.process(templatePath, context)

        try {
            val helper = MimeMessageHelper(message, true, "UTF-8")
            helper.setFrom("dsmddns2022@naver.com")
            helper.setSubject(title)
            helper.setTo(to)
            helper.setText(content, true)
        } catch (e: MessagingException) {
            throw MailSendingException("SMTP 를 통해 메일 발송중 오류가 발생하였습니다!")
        }
        jms.send(message)
    }


}
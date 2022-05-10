package com.ddns.dsqlbackendspringv1.domain.auth.business.service

import com.ddns.dsqlbackendspringv1.domain.auth.exception.NotPermitEmailException
import com.ddns.dsqlbackendspringv1.domain.auth.business.service.emailCheck.PermitEmailListProperty
import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.token.EmailCheckCode
import com.ddns.dsqlbackendspringv1.domain.auth.data.repository.token.EmailCheckCodeRepository
import com.ddns.dsqlbackendspringv1.global.util.code.RandomCodeGenerator
import com.ddns.dsqlbackendspringv1.infra.alarm.mail.MailService
import org.springframework.stereotype.Service


@Service
class EmailServiceImpl(
    private val mailService: MailService,
    private val random: RandomCodeGenerator,
    private val emailCheckCodeRepository: EmailCheckCodeRepository,
    private val emailList: PermitEmailListProperty
): EmailService {

    companion object{
        const val CODE_LENGTH = 4
        const val AUTH_MAIL_TITLE = "[DSQL] 인증번호입니다."
    }

    override fun sendCodeToEmail(email: String) {
        if (emailList.email.contains(email)) {
            val random = random.geneCode(CODE_LENGTH)
            val emailCheckCode = EmailCheckCode(email, random)
            emailCheckCodeRepository.save(emailCheckCode)
            val map = HashMap<String, String>()
            map.put("code", random)
            mailService.sendHtmlMail(email, AUTH_MAIL_TITLE, "mail.html", map)
        } else throw NotPermitEmailException(email)
    }


}
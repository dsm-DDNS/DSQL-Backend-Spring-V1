package com.ddns.dsqlbackendspringv1.domain.auth.business.service

import com.ddns.dsqlbackendspringv1.domain.auth.data.token.EmailCheckCode
import com.ddns.dsqlbackendspringv1.domain.auth.data.repository.token.EmailCheckCodeRepository
import com.ddns.dsqlbackendspringv1.domain.auth.data.repository.user.UserRepository
import com.ddns.dsqlbackendspringv1.domain.auth.exception.UserAlreadyExistsException
import com.ddns.dsqlbackendspringv1.infra.alarm.mail.MailService
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service


@Service
class EmailServiceImpl(
    private val mailService: MailService,
    private val emailCheckCodeRepository: EmailCheckCodeRepository,
    private val userRepository: UserRepository
): EmailService {

    companion object{
        const val CODE_LENGTH = 4
        const val AUTH_MAIL_TITLE = "[DSQL] 인증번호입니다."
    }
    private val emailList: MutableList<String> = ArrayList<String>()

    override fun sendCodeToEmail(email: String) {
//        if (emailList.email.contains(email)) {
        userRepository.findByEmail(email).orElse(null)?. let {
            throw UserAlreadyExistsException(email)
        }
        val random = RandomStringUtils.randomNumeric(CODE_LENGTH)
        val emailCheckCode = EmailCheckCode(email, random)
        emailCheckCodeRepository.save(emailCheckCode)
        val map = HashMap<String, String>()
        map.put("code", random)
        mailService.sendHtmlMail(email, AUTH_MAIL_TITLE, "mail.html", map)
//        } else throw NotPermitEmailException(email)

    }

}
package com.ddns.dsqlbackendspringv1.infra.alarm.mail.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class MailSendingException(data: String): GlobalError(ErrorCode.EMAIL_ERROR, data) {
}
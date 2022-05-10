package com.ddns.dsqlbackendspringv1.domain.auth.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class EmailCheckCodeException(data: String): GlobalError(ErrorCode.CHECK_EMAIL_CODE_ERROR, data) {
}
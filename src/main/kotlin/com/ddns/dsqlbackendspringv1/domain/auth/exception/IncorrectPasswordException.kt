package com.ddns.dsqlbackendspringv1.domain.auth.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class IncorrectPasswordException(data: String): GlobalError(ErrorCode.INCORRECT_PASSWORD, data) {
}
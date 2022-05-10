package com.ddns.dsqlbackendspringv1.domain.auth.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class NotPermitEmailException(data: String): GlobalError(ErrorCode.NOT_PERMIT_EMAIL_ERROR, data) {
}
package com.ddns.dsqlbackendspringv1.global.security.jwt.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class InvalidTokenException(data: String): GlobalError(ErrorCode.INVALID_TOKEN, data) {
}
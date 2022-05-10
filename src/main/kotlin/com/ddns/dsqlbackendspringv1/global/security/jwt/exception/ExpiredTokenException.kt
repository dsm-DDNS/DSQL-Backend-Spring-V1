package com.ddns.dsqlbackendspringv1.global.security.jwt.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError


class ExpiredTokenException(data: String): GlobalError(ErrorCode.EXPIRED_TOKEN, data) {
}
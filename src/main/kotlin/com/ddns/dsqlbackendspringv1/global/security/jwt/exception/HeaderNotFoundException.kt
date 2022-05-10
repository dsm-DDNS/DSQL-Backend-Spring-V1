package com.ddns.dsqlbackendspringv1.global.security.jwt.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class HeaderNotFoundException(data: String): GlobalError(ErrorCode.HEADER_NOT_FOUND, data) {
}
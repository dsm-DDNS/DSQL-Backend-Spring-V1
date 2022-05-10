package com.ddns.dsqlbackendspringv1.domain.auth.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class UserNotFoundException(data: String): GlobalError(ErrorCode.USER_NOT_FOUND, data) {
}
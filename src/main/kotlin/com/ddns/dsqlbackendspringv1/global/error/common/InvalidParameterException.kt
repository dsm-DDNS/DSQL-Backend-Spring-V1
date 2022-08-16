package com.ddns.dsqlbackendspringv1.global.error.common

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class InvalidParameterException(data: String): GlobalError(ErrorCode.INVALID_PARAMETER, data) {
}
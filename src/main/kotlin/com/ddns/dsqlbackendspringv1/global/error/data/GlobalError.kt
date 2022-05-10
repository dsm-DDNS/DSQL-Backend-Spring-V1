package com.ddns.dsqlbackendspringv1.global.error.data

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode

open class GlobalError(
    val errorCode: ErrorCode,
    val data: String
    ): RuntimeException(errorCode.message) {
}
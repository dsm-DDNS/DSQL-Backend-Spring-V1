package com.ddns.dsqlbackendspringv1.infra.image.s3.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class S3Exception(data: String): GlobalError(ErrorCode.S3_ERROR, data) {
}
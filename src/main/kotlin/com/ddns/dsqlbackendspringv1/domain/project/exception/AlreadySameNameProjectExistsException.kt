package com.ddns.dsqlbackendspringv1.domain.project.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class AlreadySameNameProjectExistsException(data: String): GlobalError(ErrorCode.ALREADY_SAME_NAME_PROJECT_EXISTS, data){
}
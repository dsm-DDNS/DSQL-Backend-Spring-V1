package com.ddns.dsqlbackendspringv1.domain.project.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class ProjectNotFound(data: String): GlobalError(ErrorCode.PROJECT_NOT_FOUND, data) {
}
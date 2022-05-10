package com.ddns.dsqlbackendspringv1.domain.news.exception

import com.ddns.dsqlbackendspringv1.global.error.ErrorCode
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError

class NewsNotFoundException(data: Long): GlobalError(ErrorCode.NEWS_NOT_FOUND, data.toString()) {
}
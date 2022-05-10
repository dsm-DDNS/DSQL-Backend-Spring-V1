package com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.ShortNewsDto

data class ShortNewsListResponse(
    val newsList: List<ShortNewsDto>,
    val size: Int
)

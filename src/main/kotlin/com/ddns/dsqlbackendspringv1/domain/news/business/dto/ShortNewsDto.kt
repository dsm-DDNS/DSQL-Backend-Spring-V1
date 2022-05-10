package com.ddns.dsqlbackendspringv1.domain.news.business.dto

import com.ddns.dsqlbackendspringv1.domain.auth.business.dto.MiniUserDto

data class ShortNewsDto (
    val title: String,
    val shortContent: String,
    val writer: MiniUserDto
)
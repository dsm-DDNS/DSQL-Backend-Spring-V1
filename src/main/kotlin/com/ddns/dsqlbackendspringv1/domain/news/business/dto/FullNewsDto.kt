package com.ddns.dsqlbackendspringv1.domain.news.business.dto

import com.ddns.dsqlbackendspringv1.domain.auth.business.dto.FullUserDto
import com.ddns.dsqlbackendspringv1.domain.auth.business.dto.MiniUserDto
import java.time.LocalDate

data class FullNewsDto (
    val title: String,
    val shortContent: String,
    val content: String,
    val writer: FullUserDto,
    val createAt: LocalDate

)
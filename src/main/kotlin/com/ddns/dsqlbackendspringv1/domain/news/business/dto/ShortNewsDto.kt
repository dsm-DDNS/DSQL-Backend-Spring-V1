package com.ddns.dsqlbackendspringv1.domain.news.business.dto

import com.ddns.dsqlbackendspringv1.domain.auth.business.dto.MiniUserDto
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image

data class ShortNewsDto (
    val title: String,
    val shortContent: String,
    val writer: MiniUserDto,
    val imageList: List<Image>
)
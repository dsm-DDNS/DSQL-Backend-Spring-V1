package com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User


data class GenerateNewsRequest(
    val title: String,
    val shortContent: String,
    val content: String,
)

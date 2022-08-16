package com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request



data class GenerateNewsRequest(
    val title: String,
    val shortContent: String,
    val content: String,
)

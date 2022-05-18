package com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request

data class EditNewsRequest(
    val title: String,
    val content: String,
    val shortContent: String,
)

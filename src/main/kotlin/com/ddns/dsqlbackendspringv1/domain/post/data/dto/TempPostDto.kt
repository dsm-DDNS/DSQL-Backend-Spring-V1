package com.ddns.dsqlbackendspringv1.domain.post.data.dto

data class TempPostDto (
    val title: String,
    val url: String,
    val createAt: String,
    val content: String?,
    val shortContent: String?,
    val tags: String?,
    val img: String
)

package com.ddns.dsqlbackendspringv1.domain.post.business.dto

import java.time.LocalDate

data class FullPostDto (
    val title: String,
    val url: String,
    val createAt: LocalDate,
    val content: String?,
    val shortContent: String?,
    val tags: List<String>?,
    val img: List<String>?
)
package com.ddns.dsqlbackendspringv1.domain.post.business.dto

data class ShortPostDto (
    val title: String,
    val shortContent: String?,
    val imgList: List<String>?
)
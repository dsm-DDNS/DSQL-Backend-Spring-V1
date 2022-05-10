package com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.UrlInfo

data class AddDevRequest(
    val name: String,
    val studentId: String,
    val email: String,
    val urlInfo: UrlInfo
)

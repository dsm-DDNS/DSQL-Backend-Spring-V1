package com.ddns.dsqlbackendspringv1.domain.project.business.dto

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.UrlInfo

data class ShortProject (
    val title: String,
    val img: List<Image>,
    val introduction: String,
    val urlList: List<UrlInfo>,
    val logo: Image
)
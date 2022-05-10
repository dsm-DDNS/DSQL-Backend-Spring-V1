package com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Developer
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.UrlInfo
import java.time.LocalDate

data class RegisterProjectRequest(
    val title: String,
    val introduction:String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val devList: List<Developer>,
    val urlInfo: List<UrlInfo>,
    val imgList: List<Image>


)

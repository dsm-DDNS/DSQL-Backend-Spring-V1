package com.ddns.dsqlbackendspringv1.domain.project.business.dto

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Developer
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import java.time.LocalDate

data class FullProjectDto(
    val projectId: Long,
    val title: String,
    val introduction: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val devList: List<Developer>,
    val imgList: List<Image>,
    val logo: Image,
)

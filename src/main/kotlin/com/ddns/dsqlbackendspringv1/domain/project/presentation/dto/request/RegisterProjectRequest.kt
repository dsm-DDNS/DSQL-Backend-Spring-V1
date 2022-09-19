package com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Developer
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.UrlInfo
import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import javax.validation.constraints.Min

data class RegisterProjectRequest(
    @field:Length(min = 2, message = "title length must upper than 1.")
    val title: String,
    val introduction:String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val devList: List<Developer>,
    val urlInfo: List<UrlInfo>


)

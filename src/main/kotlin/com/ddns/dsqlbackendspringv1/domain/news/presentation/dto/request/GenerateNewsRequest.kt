package com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Max
import kotlin.math.max


data class GenerateNewsRequest(
    val title: String,
    @field:Length(min=2, max=20, message = "ShortContent must be at least 2 and less than 20 in length.")
    val shortContent: String,
    val content: String,
)

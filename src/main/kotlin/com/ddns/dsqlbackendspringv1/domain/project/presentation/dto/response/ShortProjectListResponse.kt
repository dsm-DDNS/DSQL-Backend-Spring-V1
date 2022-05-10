package com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response

import com.ddns.dsqlbackendspringv1.domain.project.business.dto.ShortProject
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Project

data class ShortProjectListResponse(
    val project: List<ShortProject>,
    val size: Int

)

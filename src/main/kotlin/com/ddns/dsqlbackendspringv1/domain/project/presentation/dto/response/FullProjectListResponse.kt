package com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response

import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto

data class FullProjectListResponse(
    val projectList: List<FullProjectDto>,
    val size: Int

)

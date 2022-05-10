package com.ddns.dsqlbackendspringv1.domain.post.presentation.dto.response

import com.ddns.dsqlbackendspringv1.domain.post.business.dto.ShortPostDto

data class ShortPostListResponse(
    val shortPostList: List<ShortPostDto>,
    val count: Int
)

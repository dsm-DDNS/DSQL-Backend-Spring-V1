package com.ddns.dsqlbackendspringv1.domain.post.business.service

import com.ddns.dsqlbackendspringv1.domain.post.presentation.dto.response.OneFullPostResponse
import com.ddns.dsqlbackendspringv1.domain.post.presentation.dto.response.ShortPostListResponse

interface PostService {

    fun getLatestPost(): OneFullPostResponse
    fun getPostListOrderByDesc(size: Int): ShortPostListResponse

}
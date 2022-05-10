package com.ddns.dsqlbackendspringv1.domain.news.business.service

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.GenerateNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response.ShortNewsListResponse

interface NewsService {

    fun getShortNewsList(): ShortNewsListResponse
    fun getOneFullNewsList(id: Long): FullNewsDto
    fun getLatestFullNewsList(): FullNewsDto
    fun generateNews(request: GenerateNewsRequest)
}
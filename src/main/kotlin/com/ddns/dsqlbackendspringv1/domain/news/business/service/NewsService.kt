package com.ddns.dsqlbackendspringv1.domain.news.business.service

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.ShortNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.EditNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.GenerateNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response.ShortNewsListResponse
import org.springframework.data.domain.Page
import org.springframework.web.multipart.MultipartFile

interface NewsService {

    fun getShortNewsList(idx: Int, size: Int): Page<FullNewsDto>
    fun getOneFullNewsList(id: Long): FullNewsDto
    fun getLatestFullNewsList(): FullNewsDto
    fun generateNews(request: GenerateNewsRequest)

    fun editNews(newsId: Long, request: EditNewsRequest): FullNewsDto

    fun deleteNews(newsId: Long)

    fun addNewsImage(newsId: Long, imageList: List<MultipartFile>)

    fun removeNewsImage(newsId: Long, imageUrl: String)
}
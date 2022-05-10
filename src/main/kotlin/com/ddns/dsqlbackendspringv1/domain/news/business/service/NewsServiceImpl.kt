package com.ddns.dsqlbackendspringv1.domain.news.business.service

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.data.entity.News
import com.ddns.dsqlbackendspringv1.domain.news.data.repository.NewsRepository
import com.ddns.dsqlbackendspringv1.domain.news.exception.NewsNotFoundException
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.GenerateNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response.ShortNewsListResponse
import com.ddns.dsqlbackendspringv1.global.util.user.UserCheckUtil
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class NewsServiceImpl(
    private val newsRepository: NewsRepository,
    private val userCheckUtil: UserCheckUtil

): NewsService {
    override fun getShortNewsList(): ShortNewsListResponse {
        val shortNewsList = newsRepository.findAll(PageRequest.of(1, 40)).stream().map {
            it.toShortNews()
        }.toList()

        return ShortNewsListResponse(
            shortNewsList,
            shortNewsList.size
        )
    }

    override fun getOneFullNewsList(id: Long): FullNewsDto {
        return (newsRepository.findById(id).orElse(null)?: throw NewsNotFoundException(id)).toFullNewsDto()
    }

    override fun getLatestFullNewsList(): FullNewsDto {
        return newsRepository.findFirstByOrderByCreateAtDesc().toFullNewsDto()
    }

    override fun generateNews(request: GenerateNewsRequest) {
        val user = userCheckUtil.getCurrentUser()
        newsRepository.save(
            News(
                request.title,
                request.shortContent,
                request.content,
                user
            )
        )
    }


}
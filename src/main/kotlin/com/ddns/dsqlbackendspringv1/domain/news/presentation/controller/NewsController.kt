package com.ddns.dsqlbackendspringv1.domain.news.presentation.controller

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.service.NewsService
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.GenerateNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response.ShortNewsListResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/dsql/v1/news")
class NewsController(
    private val newsService: NewsService
) {


    @GetMapping("/list")
    fun getShortNewsList(): ShortNewsListResponse {
        return newsService.getShortNewsList()
    }

    @GetMapping
    fun getOneFullNews(@RequestParam newsId: Long): FullNewsDto {
        return newsService.getOneFullNewsList(newsId)
    }

    @GetMapping
    fun getLatestFullNews(): FullNewsDto {
        return newsService.getLatestFullNewsList()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun generateNews(@RequestBody request: GenerateNewsRequest) {
        return newsService.generateNews(request)
    }

}
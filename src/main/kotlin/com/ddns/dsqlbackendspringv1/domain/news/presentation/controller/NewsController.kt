package com.ddns.dsqlbackendspringv1.domain.news.presentation.controller

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.ShortNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.service.NewsService
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.DeleteImageNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.GenerateNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response.ShortNewsListResponse
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.security.InvalidParameterException


@RestController
@RequestMapping("/api/dsql/v1/news")
class NewsController(
    private val newsService: NewsService
) {


    @GetMapping("/list")
    fun getNewsList(@RequestParam(defaultValue = "0", required = false) idx: Int,@RequestParam(defaultValue = "5", required = false) size: Int): Page<FullNewsDto> {
        return newsService.getShortNewsList(idx, size)
    }

    @GetMapping
    fun getOneFullNews(@RequestParam(required = false) newsId: Long?): FullNewsDto {
        return newsService.getOneFullNewsList(newsId?: throw com.ddns.dsqlbackendspringv1.global.error.common.InvalidParameterException("NULL"))
    }

    @GetMapping("/latest")
    fun getLatestFullNews(): FullNewsDto {
        return newsService.getLatestFullNewsList()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun generateNews(@RequestBody @Validated request: GenerateNewsRequest) {
        return newsService.generateNews(request)
    }

    @DeleteMapping("/img")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNews(@RequestParam newsId: Long, @RequestBody request: DeleteImageNewsRequest) {
        return newsService.removeNewsImage(newsId, request.imageUrl)
    }

    @PutMapping("/img")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun addImage(@RequestParam(required = true) newsId: Long, @RequestPart(name = "image", required = true) imageList: MutableList<MultipartFile>) {
        return newsService.addNewsImage(newsId, imageList)
    }



}
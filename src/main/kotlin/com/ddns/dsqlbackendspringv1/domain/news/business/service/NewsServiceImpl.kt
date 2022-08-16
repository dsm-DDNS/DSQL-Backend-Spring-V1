package com.ddns.dsqlbackendspringv1.domain.news.business.service

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.ShortNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.data.entity.News
import com.ddns.dsqlbackendspringv1.domain.news.data.repository.NewsRepository
import com.ddns.dsqlbackendspringv1.domain.news.exception.NewsNotFoundException
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.EditNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.GenerateNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response.ShortNewsListResponse
import com.ddns.dsqlbackendspringv1.global.util.image.UploadFileService
import com.ddns.dsqlbackendspringv1.global.util.user.UserCheckUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.sql.Time
import java.time.LocalDate
import kotlin.streams.toList

@Service
class NewsServiceImpl(
    private val newsRepository: NewsRepository,
    private val current: UserCheckUtil,
    private val uploadFileService: UploadFileService

): NewsService {
    override fun getShortNewsList(idx: Int, size: Int): Page<ShortNewsDto> {
        return newsRepository.findAllByOrderByCreatedDateDesc(PageRequest.of(idx, size)).map {
            it.toShortNews()
        }
    }

    override fun getOneFullNewsList(id: Long): FullNewsDto {
        return (newsRepository.findById(id).orElse(null)?: throw NewsNotFoundException(id)).toFullNewsDto()
    }

    override fun getLatestFullNewsList(): FullNewsDto {
        return newsRepository.findFirstByOrderByCreatedDateDesc().toFullNewsDto()
    }

    override fun generateNews(request: GenerateNewsRequest) {
        val user = current.getCurrentUser()
        val news = News(
            request.title,
            request.shortContent,
            request.content,
            user,
        )

        newsRepository.save(news)
    }

    override fun editNews(newsId: Long, request: EditNewsRequest): FullNewsDto {
        val user = current.getCurrentUser()
        val news = newsRepository.findByIdAndWriter(newsId, user).orElse(null)?: throw NewsNotFoundException(newsId)
        news.editNews(request)
        newsRepository.save(news)
        return news.toFullNewsDto()
    }

    override fun deleteNews(newsId: Long) {
        val user = current.getCurrentUser()
        val news = newsRepository.findByIdAndWriter(newsId, user).orElse(null)?: throw NewsNotFoundException(newsId)
        newsRepository.delete(news)
    }

    @Transactional
    override fun addNewsImage(newsId: Long, imageList: List<MultipartFile>) {
        val user = current.getCurrentUser()
        val news = newsRepository.findByIdAndWriter(newsId, user).orElse(null)?: throw NewsNotFoundException(newsId)

        val addedImageNews = uploadFileService.uploadImageList(imageList, news)

        newsRepository.save(addedImageNews as News)
    }

    @Transactional
    override fun removeNewsImage(newsId: Long, imageUrl: String) {
        val user = current.getCurrentUser()
        val news = newsRepository.findByIdAndWriter(newsId, user).orElse(null)?: throw NewsNotFoundException(newsId)

        val removedImageNews = uploadFileService.removeImage(user, imageUrl, news)

        newsRepository.save(removedImageNews as News)
    }


}
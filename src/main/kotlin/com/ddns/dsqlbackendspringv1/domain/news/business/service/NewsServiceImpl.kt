package com.ddns.dsqlbackendspringv1.domain.news.business.service

import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.data.entity.News
import com.ddns.dsqlbackendspringv1.domain.news.data.repository.NewsRepository
import com.ddns.dsqlbackendspringv1.domain.news.exception.NewsNotFoundException
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.EditNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.GenerateNewsRequest
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.response.ShortNewsListResponse
import com.ddns.dsqlbackendspringv1.global.util.image.UploadFileService
import com.ddns.dsqlbackendspringv1.global.util.user.UserCheckUtil
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@Service
class NewsServiceImpl(
    private val newsRepository: NewsRepository,
    private val current: UserCheckUtil,
    private val uploadFileService: UploadFileService

): NewsService {
    override fun getShortNewsList(idx: Int, size: Int): ShortNewsListResponse {
        val shortNewsList = newsRepository.findAll(PageRequest.of(idx, size)).stream().map {
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

    override fun generateNews(request: GenerateNewsRequest, imageList: List<MultipartFile>?) {
        val user = current.getCurrentUser()
        val news = News(
            request.title,
            request.shortContent,
            request.content,
            user,
            LocalDate.now()
        )

        newsRepository.save(news)

        val uploadImageNews = uploadFileService.uploadImageList(imageList, news)

        newsRepository.save(
            uploadImageNews as News
        )

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

    override fun addNewsImage(newsId: Long, imageList: List<MultipartFile>) {
        val user = current.getCurrentUser()
        val news = newsRepository.findByIdAndWriter(newsId, user).orElse(null)?: throw NewsNotFoundException(newsId)

        val addedImageNews = uploadFileService.uploadImageList(imageList, news)

        newsRepository.save(addedImageNews as News)
    }

    override fun removeNewsImage(newsId: Long, imageUrl: String) {
        val user = current.getCurrentUser()
        val news = newsRepository.findByIdAndWriter(newsId, user).orElse(null)?: throw NewsNotFoundException(newsId)

        val removedImageNews = uploadFileService.removeImage(user, imageUrl, news)

        newsRepository.save(removedImageNews as News)
    }


}
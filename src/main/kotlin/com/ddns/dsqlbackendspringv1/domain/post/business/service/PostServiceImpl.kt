package com.ddns.dsqlbackendspringv1.domain.post.business.service

import com.ddns.dsqlbackendspringv1.domain.post.data.repository.BatchPostDao
import com.ddns.dsqlbackendspringv1.domain.post.presentation.dto.response.OneFullPostResponse
import com.ddns.dsqlbackendspringv1.domain.post.presentation.dto.response.ShortPostListResponse
import com.ddns.dsqlbackendspringv1.global.batch.config.JobConfiguration
import com.ddns.dsqlbackendspringv1.global.database.DatabaseConfiguration
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class PostServiceImpl(
    private val batchPostDao: BatchPostDao
): PostService {
    override fun getLatestPost(): OneFullPostResponse {
        val post = batchPostDao.getLatestPost().toFullPostDto()
        return OneFullPostResponse(
            post
        )
    }

    override fun getPostListOrderByDesc(size: Int): ShortPostListResponse {
        val postList = batchPostDao.getBatchPostList(size).stream().map { it.toShortPostDto() }.toList()

        return ShortPostListResponse(
            postList,
            postList.size
        )
    }
}
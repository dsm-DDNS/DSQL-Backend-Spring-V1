package com.ddns.dsqlbackendspringv1.domain.post.data.repository

import com.ddns.dsqlbackendspringv1.domain.post.data.entity.Post
import com.ddns.dsqlbackendspringv1.domain.post.data.mapper.TempPostMapper
import com.ddns.dsqlbackendspringv1.global.batch.config.JobConfiguration
import com.ddns.dsqlbackendspringv1.global.database.DatabaseConfiguration
import com.ddns.dsqlbackendspringv1.global.util.textParsing.ContentTextParsingUtil
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class BatchPostDao(
    private val dbConfig: DatabaseConfiguration,
    private val textParsingUtil: ContentTextParsingUtil,
    private val jdbcTemplate: JdbcTemplate,
) {


    fun getBatchPostList(size: Int): List<Post> {
        return getPostList(size)
    }

    fun getLatestPost(): Post {
        return getBatchPostList(1)[0]
    }

    fun getPostList(size: Int): List<Post> {
        val query = "SELECT `title`, `url`, `create_at`, `content`, `short_content`, `tags`, `img` FROM `POST` ORDER BY `create_at` DESC LIMIT ?;"
        return jdbcTemplate.query(query, TempPostMapper(), size).map {
            Post(
                it.title,
                it.url,
                it.createAt,
                it.content,
                it.shortContent,
                it.tags,
                textParsingUtil.getLinkList(it.img)
            )
        }
    }

}
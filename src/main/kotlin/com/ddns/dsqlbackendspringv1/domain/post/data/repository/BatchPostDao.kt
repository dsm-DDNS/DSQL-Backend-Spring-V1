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
        val postList = dbConfig.jdbcTemplate().query(
            "SELECT * FROM ? ORDER BY ? DESC LIMIT ?", TempPostMapper()
            , {JobConfiguration.WRITE_TABLE_NAME}
            , {JobConfiguration.CREATE_AT_COLUMN}
            , size
        ).stream().map {
            Post(
                it.title,
                it.url,
                it.createAt,
                it.content,
                it.shortContent,
                it.tags,
                textParsingUtil.getLinkList(it.img),
            )
        }.toList()

        return postList
    }

    fun getLatestPost(): Post {
        val query = "SELECT `title`, `url`, `create_at`, `content`, `short_content`, `tags`, `img` FROM `POST` ORDER BY `create_at` DESC LIMIT 1;"
        val post = jdbcTemplate.query(query, TempPostMapper()).map {
            Post(
                it.title,
                it.url,
                it.createAt,
                it.content,
                it.shortContent,
                it.tags,
                textParsingUtil.getLinkList(it.img)
            )
        }[0]

        return post
    }

}
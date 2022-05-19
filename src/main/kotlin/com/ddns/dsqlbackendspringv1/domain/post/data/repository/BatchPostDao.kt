package com.ddns.dsqlbackendspringv1.domain.post.data.repository

import com.ddns.dsqlbackendspringv1.domain.post.data.entity.Post
import com.ddns.dsqlbackendspringv1.domain.post.data.mapper.TempPostMapper
import com.ddns.dsqlbackendspringv1.global.batch.config.JobConfiguration
import com.ddns.dsqlbackendspringv1.global.database.DatabaseConfiguration
import com.ddns.dsqlbackendspringv1.global.util.textParsing.ContentTextParsingUtil
import org.springframework.stereotype.Repository

@Repository
class BatchPostDao(
    private val dbConfig: DatabaseConfiguration,
    private val textParsingUtil: ContentTextParsingUtil
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
        val post = dbConfig.jdbcTemplate().query(
            "SELECT * FROM ? ORDER BY ? DESC LIMIT 1", TempPostMapper()
            , {JobConfiguration.WRITE_TABLE_NAME}
            , {JobConfiguration.CREATE_AT_COLUMN}
        ).map {
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
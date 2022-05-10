package com.ddns.dsqlbackendspringv1.global.batch.data.mapper

import com.ddns.dsqlbackendspringv1.global.batch.data.entity.BatchPost
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet


class PostRowMapper: RowMapper<BatchPost> {

    companion object {
        const val TITLE_COLUMN = "title"
        const val URL_COLUMN = "url"
        const val CREATE_AT_COLUMN = "create_at"
        const val CONTENT_COLUMN = "content"
        const val IMG_COLUMN = "img"
    }

    override fun mapRow(rs: ResultSet, rowNum: Int): BatchPost {
        return BatchPost(
            rs.getString(TITLE_COLUMN),
            rs.getString(URL_COLUMN),
            rs.getString(CREATE_AT_COLUMN),
            rs.getString(CONTENT_COLUMN),
            rs.getString(IMG_COLUMN)
        )
    }


}
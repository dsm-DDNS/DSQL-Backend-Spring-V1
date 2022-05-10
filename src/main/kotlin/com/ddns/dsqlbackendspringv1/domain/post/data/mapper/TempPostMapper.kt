package com.ddns.dsqlbackendspringv1.domain.post.data.mapper

import com.ddns.dsqlbackendspringv1.domain.post.data.dto.TempPostDto
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet


class TempPostMapper: RowMapper<TempPostDto> {

    companion object {
        const val TITLE_COLUMN = "title"
        const val URL_COLUMN = "url"
        const val CREATE_AT_COLUMN = "create_at"
        const val CONTENT_COLUMN = "content"
        const val SHORT_CONTENT_COLUMN = "short_content"
        const val TAGS_COLUMN = "tags"
        const val IMG_COLUMN = "img"

    }


    override fun mapRow(rs: ResultSet, rowNum: Int): TempPostDto {
        return TempPostDto(
            rs.getString(TITLE_COLUMN),
            rs.getString(URL_COLUMN),
            rs.getString(CREATE_AT_COLUMN),
            rs.getString(CONTENT_COLUMN),
            rs.getString(SHORT_CONTENT_COLUMN),
            rs.getString(TAGS_COLUMN),
            rs.getString(IMG_COLUMN)
        )
    }



}
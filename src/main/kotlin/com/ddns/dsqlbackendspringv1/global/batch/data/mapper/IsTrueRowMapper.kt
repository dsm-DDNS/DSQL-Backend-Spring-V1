package com.ddns.dsqlbackendspringv1.global.batch.data.mapper

import com.ddns.dsqlbackendspringv1.global.batch.data.dto.IsTrue
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class IsTrueRowMapper: RowMapper<IsTrue> {

    override fun mapRow(rs: ResultSet, rowNum: Int): IsTrue {
        return IsTrue(
            rs.getBoolean("isTrue")
        )
    }

}
package com.ddns.dsqlbackendspringv1.domain.news.data.entity

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.ShortNewsDto
import com.ddns.dsqlbackendspringv1.global.base.entity.BaseTimeEntity
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
class News(
    title: String,
    shortContent: String,
    content: String,
    writer: User
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var title: String = title

    var shortContent: String = shortContent

    var content:String = content

    @ManyToOne
    @JoinColumn(name = "writer_id")
    var writer: User = writer

    val createAt: LocalDate? = null

    fun toShortNews(): ShortNewsDto {
        return ShortNewsDto(
            this.title,
            this.shortContent,
            this.writer.toMiniUserDto()
        )
    }

    fun toFullNewsDto(): FullNewsDto {
        return FullNewsDto(
            this.title,
            this.shortContent,
            this.content,
            this.writer.toFullUserDto(),
            this.createAt!!
            )
    }

}
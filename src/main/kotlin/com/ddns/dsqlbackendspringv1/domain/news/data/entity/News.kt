package com.ddns.dsqlbackendspringv1.domain.news.data.entity

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.ShortNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.EditNewsRequest
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.global.base.entity.BaseTimeEntity
import com.ddns.dsqlbackendspringv1.global.base.entity.UploadFile
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
class News(
    title: String,
    content: String,
    shortContent: String,
    writer: User,
    createAt: LocalDate
): UploadFile(
    title,
    content
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var shortContent: String = shortContent

    @ManyToOne
    @JoinColumn(name = "writer_id")
    var writer: User = writer

    @Column(name = "news_create_date")
    var createAt: LocalDate = createAt

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
            this.createdDate!!.toLocalDate()
            )
    }

    fun editNews(request: EditNewsRequest) {
        this.title = request.title
        this.shortContent = request.shortContent
        this.content = request.content

    }

}
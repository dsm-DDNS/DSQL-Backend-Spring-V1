package com.ddns.dsqlbackendspringv1.domain.news.data.entity

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.FullNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.business.dto.ShortNewsDto
import com.ddns.dsqlbackendspringv1.domain.news.presentation.dto.request.EditNewsRequest
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.global.base.entity.BaseTimeEntity
import com.ddns.dsqlbackendspringv1.global.base.entity.UploadFile
import java.sql.Blob
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
    shortContent: String,
    content: String,
    writer: User,
): UploadFile(
    title,
    content
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name="short_content")
    var shortContent: String = shortContent

    @ManyToOne
    @JoinColumn(name = "writer_id")
    var writer: User = writer

    fun toShortNews(): ShortNewsDto {
        return ShortNewsDto(
            this.title,
            this.shortContent,
            this.writer.toMiniUserDto(),
            this.getImgList()
        )
    }

    fun toFullNewsDto(): FullNewsDto {
        return FullNewsDto(
            this.title,
            this.shortContent,
            this.content.toString(),
            this.writer.toFullUserDto(),
            this.createdDate!!.toLocalDate(),
            this.getImgList()
        )
    }

    fun editNews(request: EditNewsRequest) {
        this.title = request.title
        this.shortContent = request.shortContent
        this.content = request.content

    }

    override fun addLogoImg(img: Image) {
        this.addImg(img)
    }

    override fun getIdentity(): String {
        return this.id.toString() + "_NEWS"
    }

}
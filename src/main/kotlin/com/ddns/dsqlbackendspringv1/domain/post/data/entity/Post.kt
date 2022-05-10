package com.ddns.dsqlbackendspringv1.domain.post.data.entity

import com.ddns.dsqlbackendspringv1.domain.post.business.dto.FullPostDto
import com.ddns.dsqlbackendspringv1.domain.post.business.dto.ShortPostDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.persistence.Column
import javax.persistence.Id

class Post(
  title: String,
  url: String,
  createAt: String,
  content: String?,
  shortContent: String?,
  tags: String?,
  img: List<String>?
) {

  @Id
  val title = title

  val url = url

  val createAt: LocalDate = LocalDate.parse(createAt, DateTimeFormatter.ISO_DATE)

  val content = content

  val shortContent = shortContent

  val tags = tags

  val img: List<String>? = img

  fun toShortPostDto(): ShortPostDto {
    return ShortPostDto(
      this.title,
      this.shortContent,
      this.img
    )
  }

  fun toFullPostDto(): FullPostDto {
    val tagList: List<String>? = this.tags?.split(".")
    return FullPostDto(
      this.title,
      this.url,
      this.createAt,
      this.content,
      this.shortContent,
      tagList,
      img
    )

  }


}
package com.ddns.dsqlbackendspringv1.domain.news.data.repository

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.news.data.entity.News
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NewsRepository: JpaRepository<News, Long> {
    override fun findAll(pageable: Pageable): Page<News>
    fun findFirstByOrderByCreateAtDesc(): News
    fun findByIdAndWriter(id: Long, writer: User): Optional<News>
}
package com.ddns.dsqlbackendspringv1.domain.project.data.repository

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Project
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProjectRepository: JpaRepository<Project, Long> {
    override fun findAll(pageable: Pageable): Page<Project>
    fun findByTitle(title: String): Optional<Project>

}
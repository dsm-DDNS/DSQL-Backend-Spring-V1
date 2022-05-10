package com.ddns.dsqlbackendspringv1.domain.project.business.service

import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.UrlInfo
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddDevRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddImageRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddUrlInfoRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.RegisterProjectRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.FullProjectListResponse
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.ShortProjectListResponse

interface ProjectService {

    fun getShortProjectList(idx: Int, size: Int): ShortProjectListResponse
    fun getFullProjectListOrderByCreateAtDesc(idx: Int, size: Int): FullProjectListResponse
    fun getFullProjectById(id:Long): FullProjectDto

    //register part
    fun registerProject(request: RegisterProjectRequest)
    fun addImage(projectId: Long, request: AddImageRequest)
    fun addUrlInfo(projectId: Long, request: AddUrlInfoRequest)
    fun addDev(projectId: Long, request: AddDevRequest)
    fun removeImage(projectId: Long, imageUrl: String)
    fun removeUrlInfo(projectId: Long, urlKeyName: String)
    fun removeDev(projectId: Long, devEmail: String)



}
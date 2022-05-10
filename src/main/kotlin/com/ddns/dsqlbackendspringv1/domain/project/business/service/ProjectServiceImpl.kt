package com.ddns.dsqlbackendspringv1.domain.project.business.service

import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.business.dto.ShortProject
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Developer
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Project
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.UrlInfo
import com.ddns.dsqlbackendspringv1.domain.project.data.repository.ProjectRepository
import com.ddns.dsqlbackendspringv1.domain.project.exception.ProjectNotFound
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddDevRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddImageRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddUrlInfoRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.RegisterProjectRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.FullProjectListResponse
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.ShortProjectListResponse
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ProjectServiceImpl(
    private val projectRepository: ProjectRepository
): ProjectService {

    override fun getShortProjectList(idx: Int, size: Int): ShortProjectListResponse {
        val shortProjectList = projectRepository.findAll(PageRequest.of(idx, size))
            .stream().map { it.toShortProjectDto() }.toList()
        return ShortProjectListResponse(
            shortProjectList,
            shortProjectList.size
        )
    }

    override fun getFullProjectListOrderByCreateAtDesc(idx: Int, size: Int): FullProjectListResponse {
        val fullProjectList = projectRepository.findAll(PageRequest.of(idx, size))
            .stream().map { it.toFullProjectDto() }.toList()
        return FullProjectListResponse(
            fullProjectList,
            fullProjectList.size
        )
    }

    override fun getFullProjectById(id: Long): FullProjectDto {
        return findProject(id).toFullProjectDto()
    }

    override fun registerProject(request: RegisterProjectRequest) {
        projectRepository.findByTitle(request.title)
        val project =Project(
            request.title,
            request.introduction,
            request.startDate,
            request.endDate,
            request.devList as MutableList<Developer>,
        )

        project.addDevAll(request.devList)
        project.addUrlInfoAll(request.urlInfo)
        project.addImgAll(request.imgList)

        projectRepository.save(
            project
        )

    }

    private fun findProject(projectId: Long): Project {
        return projectRepository.findById(projectId).orElse(null)?: throw ProjectNotFound(projectId.toString())
    }

    override fun addImage(projectId: Long, request: AddImageRequest) {
        val image = Image(
            request.name,
            request.url
        )
        val project = findProject(projectId)
        project.addImg(image)

        projectRepository.save(
            project
        )
    }

    override fun addUrlInfo(projectId: Long, request: AddUrlInfoRequest) {
        val urlInfo = UrlInfo(
            request.title,
            request.url
        )
        val project = findProject(projectId)
        project.addUrlInfo(urlInfo)

        projectRepository.save(
            project
        )
    }

    override fun addDev(projectId: Long, request: AddDevRequest) {
        val dev = Developer(
            request.name,
            request.studentId,
            request.email,
            request.urlInfo
        )
        val project = findProject(projectId)
        project.addDev(dev)

        projectRepository.save(
            project
        )
    }


    @Transactional
    override fun removeImage(projectId: Long, imageUrl: String) {
        val project = findProject(projectId)
        for (img in project.imgList) {
            if (img.url.equals(imageUrl)) {
                project.removeImg(img)
            }
        }

    }

    @Transactional
    override fun removeUrlInfo(projectId: Long, urlKeyName: String) {
        val project = findProject(projectId)
        for (url in project.urlInfo) {
            if (url.title.equals(urlKeyName)) {
                project.removeUrlInfo(url)
            }
        }
    }

    @Transactional
    override fun removeDev(projectId: Long, devEmail: String) {
        val project = findProject(projectId)
        for (dev in project.devList) {
            if (dev.email.equals(devEmail)) {
                project.removeDev(dev)
            }
        }
    }


}
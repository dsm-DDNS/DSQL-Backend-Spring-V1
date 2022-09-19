package com.ddns.dsqlbackendspringv1.domain.project.business.service

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Developer
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Project
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.UrlInfo
import com.ddns.dsqlbackendspringv1.domain.project.data.repository.ProjectRepository
import com.ddns.dsqlbackendspringv1.domain.project.exception.AlreadySameNameProjectExistsException
import com.ddns.dsqlbackendspringv1.domain.project.exception.ProjectNotFound
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddDevRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.AddUrlInfoRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.RegisterProjectRequest
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.FullProjectListResponse
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.GenerateProjectResponse
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.ShortProjectListResponse
import com.ddns.dsqlbackendspringv1.global.util.image.UploadFileService
import com.ddns.dsqlbackendspringv1.global.util.user.UserCheckUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.transaction.Transactional
import kotlin.streams.toList

@Service
class ProjectServiceImpl(
    private val projectRepository: ProjectRepository,
    private val uploadFileService: UploadFileService,
    private val current: UserCheckUtil
): ProjectService {

    companion object {
        const val IMAGE_ROOT_NAME = "DSQL"
    }

    override fun getShortProjectList(idx: Int, size: Int): ShortProjectListResponse {
        val shortProjectList = projectRepository.findAll(PageRequest.of(idx, size, Sort.by("createdDate").descending()))
            .stream().map { it.toShortProjectDto() }.toList()
        return ShortProjectListResponse(
            shortProjectList,
            shortProjectList.size
        )
    }

    override fun getFullProjectListOrderByCreateAtDesc(idx: Int, size: Int): FullProjectListResponse {
        val fullProjectList = projectRepository.findAll(PageRequest.of(idx, size, Sort.by("createdDate").descending()))
            .stream().map { it.toFullProjectDto() }.toList()
        return FullProjectListResponse(
            fullProjectList,
            fullProjectList.size
        )
    }

    override fun getFullProjectById(id: Long): FullProjectDto {
        return findProject(id).toFullProjectDto()
    }

    @Transactional
    override fun registerProject(request: RegisterProjectRequest): GenerateProjectResponse {
        projectRepository.findByTitle(request.title).orElse(null)?. let {throw AlreadySameNameProjectExistsException(request.title)}

        val user = current.getCurrentUser()
        val project = Project(
            request.title,
            request.introduction,
            request.startDate,
            request.endDate,
            request.devList as MutableList<Developer>,
            user,

        )
        projectRepository.save(project)

        project.addUrlInfoAll(request.urlInfo)
        return GenerateProjectResponse(
            project.id!!,
        )

    }

    override fun updateLogo(projectId: Long, image: MultipartFile) {
        val user = current.getCurrentUser()
        val project = findProjectWithWriter(projectId, user)

        val uploadedProject = uploadFileService.uploadImageLogo(image, project)

        projectRepository.save(uploadedProject as Project)
    }


    override fun addImage(projectId: Long, imageList: List<MultipartFile>) {
        val user = current.getCurrentUser()
        val project = findProjectWithWriter(projectId, user)
        val uploadedProject = uploadFileService.uploadImageList(imageList, project)
        projectRepository.save(uploadedProject as Project)
    }

    override fun addUrlInfo(projectId: Long, request: AddUrlInfoRequest) {
        val user = current.getCurrentUser()
        val urlInfo = UrlInfo(
            request.title,
            request.url
        )
        val project = findProjectWithWriter(projectId, user)
        project.addUrlInfo(urlInfo)

        projectRepository.save(
            project
        )
    }

    override fun addDev(projectId: Long, request: AddDevRequest) {
        val user = current.getCurrentUser()
        val dev = Developer(
            request.name,
            request.studentId,
            request.email,
            request.urlInfo
        )
        val project = findProjectWithWriter(projectId, user)
        project.addDev(dev)

        projectRepository.save(
            project
        )
    }

    @Transactional
    override fun removeImage(projectId: Long, imageUrl: String) {
        val user = current.getCurrentUser()
        val project = findProjectWithWriter(projectId, user)

        val file = uploadFileService.removeImage(user, imageUrl, project)
        projectRepository.save(file as Project)
    }

    @Transactional
    override fun removeUrlInfo(projectId: Long, urlKeyName: String) {
        val user = current.getCurrentUser()
        val project = findProjectWithWriter(projectId, user)
        for (url in project.urlInfo) {
            if (url.title == urlKeyName) {
                project.removeUrlInfo(url)
            }
        }
    }

    @Transactional
    override fun removeDev(projectId: Long, devEmail: String) {
        val user = current.getCurrentUser()
        val project = findProjectWithWriter(projectId, user)
        for (dev in project.devList) {
            if (dev.email.equals(devEmail)) {
                project.removeDev(dev)
            }
        }
    }

    private fun findProject(projectId: Long): Project {
        return projectRepository.findById(projectId).orElse(null)?: throw ProjectNotFound(projectId.toString())
    }

    private fun findProjectWithWriter(projectId: Long, writer: User): Project {
        return projectRepository.findByIdAndWriter(projectId, writer).orElse(null)?: throw ProjectNotFound(projectId.toString())
    }


}
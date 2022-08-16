package com.ddns.dsqlbackendspringv1.domain.project.presentation.controller

import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.business.service.ProjectService
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.*
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.FullProjectListResponse
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.GenerateProjectResponse
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.ShortProjectListResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/dsql/v1/project")
class ProjectController(
    private val projectService: ProjectService
) {


    @GetMapping
    fun getOneFullProject(@RequestParam id: Long): FullProjectDto {
        return projectService.getFullProjectById(id)
    }

    @GetMapping("/short/list")
    fun getShortProjectList(@RequestParam(required = false, defaultValue = 1.toString()) idx: Int, size: Int): ShortProjectListResponse {
        return projectService.getShortProjectList(idx, size)
    }

    @GetMapping("/full/list")
    fun getFullProjectList(@RequestParam(required = false, defaultValue = 1.toString()) idx: Int, size: Int): FullProjectListResponse {
        return projectService.getFullProjectListOrderByCreateAtDesc(idx, size)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerProject(@RequestBody request: RegisterProjectRequest): GenerateProjectResponse {
        return projectService.registerProject(request)
    }

    @PutMapping("/img")
    fun addImage(@RequestParam projectId: Long, @RequestPart(name = "image") imageList: List<MultipartFile>) {
        return projectService.addImage(projectId, imageList)
    }

    @PutMapping("/dev")
    fun addDev(@RequestParam projectId: Long, @RequestBody request: AddDevRequest) {
        return projectService.addDev(projectId, request)
    }

    @PutMapping("/url")
    fun addUrlInfo(@RequestParam projectId: Long, @RequestBody request: AddUrlInfoRequest) {
        return projectService.addUrlInfo(projectId, request)
    }

    @DeleteMapping("/img")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeImage(@RequestParam projectId: Long, @RequestBody request: RemoveImageRequest) {
        return projectService.removeImage(projectId, request.imageUrl)
    }

    @DeleteMapping("/url")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeUrlInfo(@RequestParam projectId: Long, @RequestBody request: RemoveUrlInfoRequest){
        return projectService.removeUrlInfo(projectId, request.urlKeyName)
    }

    @DeleteMapping("/dev")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeDev(@RequestParam projectId: Long, @RequestBody request: RemoveDevRequest) {
        return projectService.removeDev(projectId, request.devEmail)
    }

}
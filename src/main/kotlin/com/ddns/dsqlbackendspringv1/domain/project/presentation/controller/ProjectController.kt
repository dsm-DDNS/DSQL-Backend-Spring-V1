package com.ddns.dsqlbackendspringv1.domain.project.presentation.controller

import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.business.service.ProjectService
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.request.*
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.FullProjectListResponse
import com.ddns.dsqlbackendspringv1.domain.project.presentation.dto.response.ShortProjectListResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/dsql/v1")
class ProjectController(
    private val projectService: ProjectService
) {

    @GetMapping("/short/list")
    fun getShortProjectList(@RequestParam idx: Int = 1,@RequestParam size: Int): ShortProjectListResponse {
        return projectService.getShortProjectList(idx, size)
    }

    @GetMapping("/full/list")
    fun getFullProjectList(@RequestParam idx: Int, size: Int): FullProjectListResponse {
        return projectService.getFullProjectListOrderByCreateAtDesc(idx, size)
    }

    @GetMapping
    fun getOneFullProject(@RequestParam(required = true) id: Long): FullProjectDto {
        return projectService.getFullProjectById(id)
    }


    @PostMapping("/project")
    fun registerProject(@RequestBody request: RegisterProjectRequest) {
        return projectService.registerProject(request)
    }

    @PutMapping("/img")
    fun addImage(@RequestParam projectId: Long, @RequestBody request: AddImageRequest) {
        return projectService.addImage(projectId, request)
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
    fun removeImage(@RequestParam projectId: Long, @RequestBody request: RemoveImageRequest) {
        return projectService.removeImage(projectId, request.imageUrl)
    }

    @DeleteMapping("/url")
    fun removeUrlInfo(@RequestParam projectId: Long, @RequestBody request: RemoveUrlInfoRequest){
        return projectService.removeUrlInfo(projectId, request.urlKeyName)
    }

    @DeleteMapping("/dev")
    fun removeDev(@RequestParam projectId: Long, @RequestBody request: RemoveDevRequest) {
        return projectService.removeDev(projectId, request.devEmail)
    }

}
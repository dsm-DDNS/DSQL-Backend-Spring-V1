package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.business.dto.ShortProject
import com.ddns.dsqlbackendspringv1.global.base.entity.UploadFile
import java.time.LocalDate
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
class Project(
    title: String,
    introduction: String,
    startDate: LocalDate,
    endDate: LocalDate,
    devList: MutableList<Developer>,
    writer: User
): UploadFile(
    title,
    introduction
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var startDate: LocalDate = startDate

    var endDate: LocalDate = endDate


    @ElementCollection
    var urlInfo: MutableList<UrlInfo> = ArrayList<UrlInfo>()

    @ElementCollection
    var devList: MutableList<Developer> = devList

    @ManyToOne
    @JoinColumn(name = "writer_id")
    val writer: User = writer

    fun addUrlInfo(urlInfo: UrlInfo) {
        this.urlInfo.add(urlInfo)
    }

    fun addUrlInfoAll(urlInfo: List<UrlInfo>) {
        this.urlInfo.addAll(urlInfo)
    }

    fun removeUrlInfo(urlInfo: UrlInfo) {
        this.urlInfo.remove(urlInfo)
    }

    fun addDev(developer: Developer) {
        this.devList.add(developer)
    }

    fun addDevAll(developerList: List<Developer>) {
        this.devList.addAll(developerList)
    }

    fun removeDev(developer: Developer) {
        this.devList.remove(developer)
    }

    fun toShortProjectDto(): ShortProject {
        return ShortProject(
            this.title,
            this.imgList,
            this.content,
            this.urlInfo
        )
    }

    fun toFullProjectDto(): FullProjectDto {
        return FullProjectDto(
            this.title,
            this.content,
            this.startDate,
            this.endDate,
            this.devList,
            this.imgList
        )
    }


}
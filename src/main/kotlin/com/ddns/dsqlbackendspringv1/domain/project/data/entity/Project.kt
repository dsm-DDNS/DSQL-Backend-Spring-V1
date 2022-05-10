package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.business.dto.ShortProject
import java.time.LocalDate
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class Project(
    title: String,
    introduction: String,
    startDate: LocalDate,
    endDate: LocalDate,
    devList: MutableList<Developer>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var title: String = title

    var introduction: String = introduction

    @ElementCollection
    var urlInfo: MutableList<UrlInfo> = ArrayList<UrlInfo>()

    var startDate: LocalDate = startDate

    var endDate: LocalDate = endDate

    @ElementCollection
    var imgList: MutableList<Image> = ArrayList<Image>()

    @ElementCollection
    var devList: MutableList<Developer> = devList

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

    fun addImg(img: Image) {
        this.imgList.add(img)
    }

    fun addImgAll(imgList: List<Image>) {
        this.imgList.addAll(imgList)
    }

    fun removeImg(img: Image) {
        this.imgList.remove(img)
    }

    fun toShortProjectDto(): ShortProject {
        return ShortProject(
            this.title,
            this.imgList,
            this.introduction,
            this.urlInfo
        )
    }

    fun toFullProjectDto(): FullProjectDto {
        return FullProjectDto(
            this.title,
            this.introduction,
            this.startDate,
            this.endDate,
            this.devList,
            this.imgList
        )
    }


}
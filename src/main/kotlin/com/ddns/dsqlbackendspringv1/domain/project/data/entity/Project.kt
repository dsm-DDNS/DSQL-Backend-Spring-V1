package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.project.business.dto.FullProjectDto
import com.ddns.dsqlbackendspringv1.domain.project.business.dto.ShortProject
import com.ddns.dsqlbackendspringv1.global.base.entity.UploadFile
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embedded
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
    writer: User,
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

    @Embedded
    var logo: Image? = null

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
            this.getImgList(),
            this.content.toString(),
            this.urlInfo,
            this.logo?: Image(
                "no exists",
                "https://cdn.pixabay.com/photo/2017/02/13/01/26/the-question-mark-2061539_960_720.png"
            )
        )
    }

    fun toFullProjectDto(): FullProjectDto {
        return FullProjectDto(
            this.id!!,
            this.title,
            this.content.toString(),
            this.startDate,
            this.endDate,
            this.devList,
            this.getImgList(),
            this.logo?: Image(
                "no exists",
                "https://cdn.pixabay.com/photo/2017/02/13/01/26/the-question-mark-2061539_960_720.png"
            )
        )
    }

    override fun addLogoImg(img: Image) {
        this.logo = img
    }

    override fun getIdentity(): String {
        return this.id.toString() + "_PROJ"
    }


}
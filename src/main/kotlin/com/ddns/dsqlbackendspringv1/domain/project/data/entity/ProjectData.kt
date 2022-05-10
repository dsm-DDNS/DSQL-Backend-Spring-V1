package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import java.net.URL
import java.time.LocalDate
import javax.persistence.ElementCollection
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
class ProjectData(
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
    var devList: MutableList<Developer> = devList

    fun addUrlInfo(urlInfoTitle: String, urlInfoData: String) {
        this.urlInfo.add(UrlInfo(urlInfoTitle, urlInfoData))
    }

    fun addDev(developer: Developer) {
        this.devList.add(developer)
    }


}
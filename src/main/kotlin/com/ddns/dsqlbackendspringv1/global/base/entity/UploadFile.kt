package com.ddns.dsqlbackendspringv1.global.base.entity

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import javax.persistence.ElementCollection
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class UploadFile(
    title: String,
    content: String
): BaseTimeEntity() {

    var title: String = title
        protected set

    var content: String = content

    @ElementCollection
    var imgList: MutableList<Image> = ArrayList<Image>()

    fun addImg(img: Image) {
        this.imgList.add(img)
    }

    fun addImgAll(imgList: List<Image>) {
        this.imgList.addAll(imgList)
    }

    fun removeImg(img: Image) {
        val idx = imgList.indexOf(img)
        this.imgList[idx].remove()

    }

}
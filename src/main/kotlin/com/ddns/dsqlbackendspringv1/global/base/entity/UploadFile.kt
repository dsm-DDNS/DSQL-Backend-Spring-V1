package com.ddns.dsqlbackendspringv1.global.base.entity

import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import java.sql.Blob
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class UploadFile(
    title: String,
    content: String
): BaseTimeEntity() {

    var title: String = title
        protected set

    @Lob@Column(name = "content")
    var content: String = content

    @ElementCollection
    private var imgList: MutableList<Image> = ArrayList<Image>()

    public fun getImgList(): List<Image> {
        return this.imgList.filter {
            !it.isDeleted
        }.toList()
    }
    fun addImg(img: Image) {
        this.imgList.add(img)
    }

    fun removeImg(img: Image) {
        val idx = imgList.indexOf(img)
        this.imgList[idx].remove()

    }

    abstract fun addLogoImg(img: Image)
    abstract fun getIdentity(): String

}
package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.Id


@Embeddable
open class Image(
    name: String,
    url: String
) {
    @Column(name = "image_name", nullable = true)
    var name: String = name
    @Column(name = "image_url", nullable = true)
    var url: String = url
    @Column(name = "image_is_deleted", nullable = true)
    var isDeleted: Boolean =  false
        protected set


    fun remove() {
        this.isDeleted = true
    }

}
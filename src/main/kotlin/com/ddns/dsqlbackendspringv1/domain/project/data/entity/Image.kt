package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.Id


@Embeddable
class Image(
    name: String,
    url: String
) {

    var name: String = name
    var url: String = url
    var isDeleted: Boolean =  false
        protected set

    fun remove() {
        this.isDeleted = true
    }

}
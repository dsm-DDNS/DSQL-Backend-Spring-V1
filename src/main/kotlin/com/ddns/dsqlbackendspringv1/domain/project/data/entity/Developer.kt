package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import javax.persistence.Embeddable
import javax.persistence.Embedded


@Embeddable
class Developer(
    name: String,
    studentId: String,
    email: String,
    urlInfo: UrlInfo
) {

    var name: String = name

    var studentId: String = studentId

    var email: String = email

    @Embedded
    var urlInfo: UrlInfo = urlInfo



}
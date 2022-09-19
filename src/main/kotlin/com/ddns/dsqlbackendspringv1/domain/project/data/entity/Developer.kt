package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.validation.constraints.Email


@Embeddable
class Developer(
    name: String,
    studentId: String,
    email: String,
    urlInfo: UrlInfo
) {

    var name: String = name

    var studentId: String = studentId
    @field:Email(message = "Input Data is not EmailType")
    var email: String = email

    var urlInfo: UrlInfo = urlInfo



}
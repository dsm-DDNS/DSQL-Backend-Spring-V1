package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import org.hibernate.validator.constraints.URL
import javax.persistence.*


@Embeddable
class UrlInfo(
    title: String,
    url: String
) {

    var title: String = title
    @field:URL(message = "Input Data is Not URL Type")
    var url: String = url

}
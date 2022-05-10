package com.ddns.dsqlbackendspringv1.domain.project.data.entity

import javax.persistence.*


@Embeddable
class UrlInfo(
    title: String,
    url: String
) {

    var title: String = title
    var url: String = url

}
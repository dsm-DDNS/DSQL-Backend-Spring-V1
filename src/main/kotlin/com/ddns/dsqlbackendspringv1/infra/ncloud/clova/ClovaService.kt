package com.ddns.dsqlbackendspringv1.infra.ncloud.clova

interface ClovaService {

    fun extractContent(title: String, content: String): String
}
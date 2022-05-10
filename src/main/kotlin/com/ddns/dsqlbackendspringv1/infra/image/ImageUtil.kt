package com.ddns.dsqlbackendspringv1.infra.image

import org.springframework.web.multipart.MultipartFile

interface ImageUtil {

    fun uploadFile(file: MultipartFile, rootPathName: String, middlePathName: String): String

}
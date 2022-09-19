package com.ddns.dsqlbackendspringv1.global.util.image

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.global.base.entity.UploadFile
import org.springframework.web.multipart.MultipartFile

interface UploadFileService {

    fun uploadImageLogo(image: MultipartFile, target: UploadFile): UploadFile

    fun uploadImageList(imageList: List<MultipartFile>, target: UploadFile): UploadFile
    fun removeImage(user: User, imageUrl: String, target: UploadFile): UploadFile

}
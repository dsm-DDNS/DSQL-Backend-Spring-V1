package com.ddns.dsqlbackendspringv1.global.util.image

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.project.business.service.ProjectServiceImpl
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.global.base.entity.UploadFile
import com.ddns.dsqlbackendspringv1.infra.image.ImageUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadFileServiceImpl(
    private val imageUtil: ImageUtil
): UploadFileService {


    override fun uploadImageList(imageList: List<MultipartFile>, target: UploadFile): UploadFile {
        val parsedImageList: MutableList<Image> = ArrayList()
        imageList.stream().map { parsedImageList.add(
            Image(
                it.originalFilename?:it.name,
                imageUtil.uploadFile(it, ProjectServiceImpl.IMAGE_ROOT_NAME, it.originalFilename?:it.name)
            )
        ) }
        parsedImageList.stream().map {
            target.addImg(it)
        }

        return target
    }

    override fun removeImage(user: User, imageUrl: String, target: UploadFile): UploadFile {
        for (img in target.imgList) {
            if (img.url.equals(imageUrl)) {
                target.removeImg(img)
            }
        }
        return target
    }
}
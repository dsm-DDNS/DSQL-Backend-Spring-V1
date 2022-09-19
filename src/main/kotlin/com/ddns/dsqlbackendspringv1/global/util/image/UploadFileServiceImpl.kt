package com.ddns.dsqlbackendspringv1.global.util.image

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.user.User
import com.ddns.dsqlbackendspringv1.domain.project.business.service.ProjectServiceImpl
import com.ddns.dsqlbackendspringv1.domain.project.data.entity.Image
import com.ddns.dsqlbackendspringv1.global.base.entity.UploadFile
import com.ddns.dsqlbackendspringv1.infra.image.ImageUtil
import org.springframework.data.auditing.CurrentDateTimeProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import kotlin.streams.toList

@Service
class UploadFileServiceImpl(
    private val imageUtil: ImageUtil
): UploadFileService {

    @Transactional(propagation = Propagation.NESTED)
    override fun uploadImageLogo(image: MultipartFile, target: UploadFile): UploadFile {
        target.addLogoImg(
            Image(
                image.originalFilename?:"UNTITLED",
                imageUtil.uploadFile(image, ProjectServiceImpl.IMAGE_ROOT_NAME, target.getIdentity())
            )

        )

        return target
    }


    @Transactional(propagation = Propagation.NESTED)
    override fun uploadImageList(imageList: List<MultipartFile>, target: UploadFile): UploadFile {
        val parsedImageList: MutableList<Image> = ArrayList()
        imageList.map {
            parsedImageList.add(
                Image(
                    it.originalFilename?:"UNTITLED",
                    imageUtil.uploadFile(it, ProjectServiceImpl.IMAGE_ROOT_NAME, target.getIdentity())
                )
            )
        }

        parsedImageList.map {
            target.addImg(it)
        }

        return target
    }


    @Transactional(propagation = Propagation.NESTED)
    override fun removeImage(user: User, imageUrl: String, target: UploadFile): UploadFile {
        for (img in target.getImgList()) {
            if (img.url == imageUrl) {
                target.removeImg(img)
            }
        }
        return target
    }
}
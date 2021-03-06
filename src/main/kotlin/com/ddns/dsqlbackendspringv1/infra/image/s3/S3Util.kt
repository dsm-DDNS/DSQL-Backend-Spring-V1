package com.ddns.dsqlbackendspringv1.infra.image.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import com.ddns.dsqlbackendspringv1.infra.image.ImageUtil
import com.ddns.dsqlbackendspringv1.infra.image.s3.env.S3Property
import com.ddns.dsqlbackendspringv1.infra.image.s3.exception.S3Exception
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile


@Component
class S3Util(
    private val s3Property: S3Property,
    private val amazonS3Client: AmazonS3Client
): ImageUtil {

    override fun uploadFile(file: MultipartFile, rootPathName: String, middlePathName: String): String {
        val fileName = s3Property.bucketName + "/$rootPathName/$middlePathName/" + file.originalFilename

        try {
            amazonS3Client.putObject(PutObjectRequest(s3Property.bucketName, fileName, file.inputStream, null)
                .withCannedAcl(CannedAccessControlList.PublicRead))
        } catch (err: Exception) {
            throw S3Exception(err.message.toString())
        }
        return getFileUrl(fileName)
    }

    fun getFileUrl(fileName: String): String {
        return amazonS3Client.getUrl(s3Property.bucketName, fileName).toString()
    }


}
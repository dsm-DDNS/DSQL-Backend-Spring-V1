package com.ddns.dsqlbackendspringv1.infra.image.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import com.ddns.dsqlbackendspringv1.infra.image.ImageUtil
import com.ddns.dsqlbackendspringv1.infra.image.s3.env.S3Property
import com.ddns.dsqlbackendspringv1.infra.image.s3.exception.S3Exception
import org.apache.commons.lang3.RandomUtils
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.util.UUID


@Component
class S3Util(
    private val s3Property: S3Property,
    private val s3: AmazonS3Client
): ImageUtil {

    override fun uploadFile(file: MultipartFile, rootPathName: String, middlePathName: String): String {
        val objectMetadata = ObjectMetadata()
        val bytes: ByteArray = IOUtils.toByteArray(file.inputStream)
        objectMetadata.contentLength = bytes.size.toLong()
        val byteArrayInputStream = ByteArrayInputStream(bytes)

        val fileName = "s3Property.bucketName/${rootPathName}/${middlePathName}/${file.originalFilename}/${UUID.randomUUID()}"

        try {
            s3.putObject(PutObjectRequest(s3Property.bucketName, fileName, byteArrayInputStream, objectMetadata))
        } catch (err: Exception) {
            throw S3Exception(err.message.toString())
        }
        return getFileUrl(fileName)
    }



    fun getFileUrl(fileName: String): String {
        return s3.getUrl(s3Property.bucketName, fileName).toString()
    }


}
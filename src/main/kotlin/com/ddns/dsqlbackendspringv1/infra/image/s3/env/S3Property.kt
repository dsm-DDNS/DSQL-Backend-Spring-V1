package com.ddns.dsqlbackendspringv1.infra.image.s3.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConfigurationProperties("cloud.aws.s3")
@ConstructorBinding
data class S3Property (
    val bucketName: String
)

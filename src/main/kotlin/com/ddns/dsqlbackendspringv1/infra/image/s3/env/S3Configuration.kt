package com.ddns.dsqlbackendspringv1.infra.image.s3.env

import com.amazonaws.services.s3.AmazonS3Client
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class S3Configuration {

    @Bean
    @Primary
    fun s3Client(): AmazonS3Client {
        return AmazonS3Client()
    }

}
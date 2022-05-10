package com.ddns.dsqlbackendspringv1.global.redis.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConfigurationProperties("spring.redis")
@ConstructorBinding
data class RedisProperty(
    val host: String,
    val port: Int

)

package com.ddns.dsqlbackendspringv1.domain.auth.data.entity.token

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash
class EmailCheckCode(
    email: String,
    code: String
) {
    @Id
    val email = email

    var code: String = code
    @TimeToLive
    var ttl: Long = 300

}
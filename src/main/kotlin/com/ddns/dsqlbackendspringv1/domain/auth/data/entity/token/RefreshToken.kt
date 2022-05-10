package com.ddns.dsqlbackendspringv1.domain.auth.data.entity.token

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive


@RedisHash
class RefreshToken(
    id: String,
    token: String
) {
    @Id
    val id = id

    var token = token
        private set
    @TimeToLive
    var ttl: Long = 604800
        private set

    fun reset(token: String) {
        this.token = token
        this.ttl = 604800
    }
}
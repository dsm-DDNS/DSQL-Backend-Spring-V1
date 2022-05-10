package com.ddns.dsqlbackendspringv1.global.time

import org.springframework.context.annotation.Configuration
import java.util.TimeZone
import javax.annotation.PostConstruct


@Configuration
class TimeZoneConfiguration {

    @PostConstruct
    fun setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }

}
package com.ddns.dsqlbackendspringv1.global.restTemplate

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfiguration {

    @Bean
    fun getRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}

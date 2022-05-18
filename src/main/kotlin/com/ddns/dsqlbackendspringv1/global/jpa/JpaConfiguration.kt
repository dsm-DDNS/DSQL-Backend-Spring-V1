package com.ddns.dsqlbackendspringv1.global.jpa

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EnableJpaRepositories(basePackages = ["com.ddns.dsqlbackendspringv1.domain"])
class JpaConfiguration {



}
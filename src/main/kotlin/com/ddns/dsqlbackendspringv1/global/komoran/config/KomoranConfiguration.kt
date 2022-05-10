package com.ddns.dsqlbackendspringv1.global.komoran.config

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL
import kr.co.shineware.nlp.komoran.core.Komoran
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KomoranConfiguration {

    @Bean
    fun getKomoran(): Komoran {
        return Komoran(DEFAULT_MODEL.FULL)
    }
}
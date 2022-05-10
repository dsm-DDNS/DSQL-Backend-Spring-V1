package com.ddns.dsqlbackendspringv1.global.util.code

import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom


@Component
class RandomCodeGenerator {

    fun geneCode(size: Int): String {
        val code = ThreadLocalRandom.current()
        return code.toString().substring(0, size - 1)
    }

}
package com.ddns.dsqlbackendspringv1.global.util.textParsing

interface ContentTextParsingUtil {

    fun extractLink(content: String): String
    fun getLinkList(str: String): List<String>?

}
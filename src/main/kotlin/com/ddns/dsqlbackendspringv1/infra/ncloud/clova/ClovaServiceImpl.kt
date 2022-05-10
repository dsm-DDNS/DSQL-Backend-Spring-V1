package com.ddns.dsqlbackendspringv1.infra.ncloud.clova

import com.ddns.dsqlbackendspringv1.global.util.textParsing.ContentTextParsingUtil
import com.ddns.dsqlbatchserver.infra.ncloud.clova.data.ClovaSummaryRequset
import com.ddns.dsqlbatchserver.infra.ncloud.clova.data.ClovaSummaryResponse
import com.ddns.dsqlbatchserver.infra.ncloud.clova.data.Document
import com.ddns.dsqlbatchserver.infra.ncloud.clova.data.Option
import com.ddns.dsqlbackendspringv1.infra.ncloud.env.NCloudProperty
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class ClovaServiceImpl(
    private val parseUtil: ContentTextParsingUtil,
    private val nCloudProperty: NCloudProperty,
    private val restTemplate: RestTemplate
): ClovaService {

    companion object {
        const val SUMMARY_URL = "https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize"
    }


    override fun extractContent(title: String, content: String): String {
        val text = parseUtil.extractLink(content).replace("\n", " ")
        if (text.length <= 30) return content

        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers["X-NCP-APIGW-API-KEY-ID"] = nCloudProperty.clientKey
        headers["X-NCP-APIGW-API-KEY"] = nCloudProperty.secretKey

        val request = ClovaSummaryRequset(
            Document(
                title,
                text
            ),
            Option(
                language = "ko",
                summaryCount = 1,
                model = "news",
                tone = null
            )
        )

        val httpEntity = HttpEntity<ClovaSummaryRequset>(request, headers)

        return restTemplate.exchange(SUMMARY_URL, HttpMethod.POST, httpEntity, ClovaSummaryResponse::class.java).body?.summary?:""
    }
}
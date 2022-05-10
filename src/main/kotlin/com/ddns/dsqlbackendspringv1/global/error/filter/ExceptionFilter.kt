package com.ddns.dsqlbackendspringv1.global.error.filter

import com.ddns.dsqlbackendspringv1.global.error.data.ErrorResponse
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ExceptionFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (err: GlobalError) {
            response.status = err.errorCode.status.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            objectMapper.writeValue(response.writer, ErrorResponse(
                err.errorCode.message,
                err.data
            ))
        }
    }


}
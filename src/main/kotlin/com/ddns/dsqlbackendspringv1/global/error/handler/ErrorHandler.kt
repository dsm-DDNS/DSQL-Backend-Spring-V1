package com.ddns.dsqlbackendspringv1.global.error.handler

import com.amazonaws.Response
import com.ddns.dsqlbackendspringv1.global.error.data.ErrorResponse
import com.ddns.dsqlbackendspringv1.global.error.data.GlobalError
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {


    @ExceptionHandler(GlobalError::class)
    fun globalExceptionHandler(error: GlobalError): ResponseEntity<*> {
        return ResponseEntity.status(error.errorCode.status).body(
            ErrorResponse(
                error.errorCode.message,
                error.data
            )
        )
    }

}
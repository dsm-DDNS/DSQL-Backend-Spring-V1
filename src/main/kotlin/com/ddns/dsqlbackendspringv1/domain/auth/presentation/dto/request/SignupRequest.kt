package com.ddns.dsqlbackendspringv1.domain.auth.presentation.dto.request

import com.ddns.dsqlbackendspringv1.domain.auth.data.entity.type.Role
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class SignupRequest(
    @Email
    val email: String,
    @Size(min = 4, max = 4, message = "EmailCheckCode는 반드시 4자여야합니다.")
    val emailCheckCode: String,
    @Valid
    val pw: String,
    @Size(min = 2, max = 20)
    @NotNull
    val name: String,
    val introduction: String,
    val userType: Role

)

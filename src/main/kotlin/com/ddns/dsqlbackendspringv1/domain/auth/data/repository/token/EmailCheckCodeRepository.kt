package com.ddns.dsqlbackendspringv1.domain.auth.data.repository.token

import com.ddns.dsqlbackendspringv1.domain.auth.data.token.EmailCheckCode
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface EmailCheckCodeRepository: CrudRepository<EmailCheckCode, String> {
}
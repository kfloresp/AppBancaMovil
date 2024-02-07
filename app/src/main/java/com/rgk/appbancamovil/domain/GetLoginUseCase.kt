package com.rgk.appbancamovil.domain

import com.rgk.appbancamovil.data.common.MessageDTO
import com.rgk.appbancamovil.data.source.repository.HomeLoginRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: HomeLoginRepository
) {
    suspend operator fun invoke(usr: String, pass: String): MessageDTO {
        return repository.getLogin(usr, pass)
    }

}
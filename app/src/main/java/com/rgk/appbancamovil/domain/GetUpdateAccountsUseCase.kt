package com.rgk.appbancamovil.domain

import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.data.source.repository.HomeAccountsRepository
import com.rgk.appbancamovil.domain.mapper.toDomain
import com.rgk.appbancamovil.domain.model.DataAccount
import javax.inject.Inject

class GetUpdateAccountsUseCase @Inject constructor(
    private val repository: HomeAccountsRepository
) {
    suspend operator fun invoke(): DataResponseDTO<DataAccount> {
        val result = DataResponseDTO<DataAccount>()
        val response = repository.getUpdateAccounts()
        response.let { model ->
            result.code = model.code
            result.messageDTO = model.messageDTO
            result.data = model.data?.map { it.toDomain() }
        }
        return result
    }

}
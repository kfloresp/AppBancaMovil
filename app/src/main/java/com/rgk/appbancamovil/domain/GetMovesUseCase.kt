package com.rgk.appbancamovil.domain

import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.data.source.repository.AccountsDetailRepository
import com.rgk.appbancamovil.domain.mapper.toDomain
import com.rgk.appbancamovil.domain.model.DataAccount
import com.rgk.appbancamovil.domain.model.DataMotion
import javax.inject.Inject

class GetMovesUseCase @Inject constructor(
    private val repository: AccountsDetailRepository
) {
    suspend operator fun invoke(id:Int): DataResponseDTO<DataMotion> {
        val result = DataResponseDTO<DataMotion>()
        val response = repository.getMoves(id)
        response.let { model ->
            result.code = model.code
            result.messageDTO = model.messageDTO
            result.data = model.data?.map { it.toDomain() }
        }
        return result
    }

}
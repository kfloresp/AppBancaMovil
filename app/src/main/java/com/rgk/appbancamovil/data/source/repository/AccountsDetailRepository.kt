package com.rgk.appbancamovil.data.source.repository

import com.rgk.appbancamovil.data.source.remote.model.DataMotionModel
import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.data.source.remote.service.AccountsDetailService
import com.rgk.appbancamovil.util.Constants
import kotlinx.coroutines.delay
import javax.inject.Inject

class AccountsDetailRepository @Inject constructor(private val service: AccountsDetailService) {
    suspend fun getMoves(id: Int):
            DataResponseDTO<DataMotionModel> {
        delay(Constants.timeDelay)
        return service.getMoves(id)
    }
}
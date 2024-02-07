package com.rgk.appbancamovil.data.source.repository

import com.rgk.appbancamovil.data.source.remote.model.DataAccountModel
import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.data.source.remote.service.HomeAccountsService
import com.rgk.appbancamovil.util.Constants
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeAccountsRepository @Inject constructor(private val service: HomeAccountsService) {
    suspend fun getAccounts(): DataResponseDTO<DataAccountModel> {
        delay(Constants.timeDelay)
        return service.getAccounts()
    }
    suspend fun getUpdateAccounts(): DataResponseDTO<DataAccountModel> {
        delay(Constants.timeDelay)
        return service.getUpdateAccounts()
    }
}
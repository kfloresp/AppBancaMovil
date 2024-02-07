package com.rgk.appbancamovil.data.source.remote.service

import android.content.Context
import com.rgk.appbancamovil.data.common.Constants
import com.rgk.appbancamovil.data.common.NetworkUtil
import com.rgk.appbancamovil.data.source.remote.model.DataAccountModel
import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.data.source.remote.network.HomeAccountsClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HomeAccountsService @Inject constructor(
    private val api : HomeAccountsClient,
    @ApplicationContext private val context: Context
){
    suspend fun getAccounts(): DataResponseDTO<DataAccountModel> {
        return withContext(Dispatchers.IO) {
            val result = DataResponseDTO<DataAccountModel>()
            if (NetworkUtil.isThereInternetConnection(context)) {
                try {
                    val response = api.getAccounts()
                    if (response != null) {
                        val body = response.body()
                        if (body != null) {
                            result.data = body.data
                            result.code = body.code
                            result.messageDTO?.apply {
                                this.errNumber = body.code
                            }
                        } else {
                            result.messageDTO?.apply {
                                errNumber = Constants.COD_ERROR_BODY_NULL
                                errMessage =  Constants.MSG_ERROR_CONEXION+ "\n Motivo-> ResponseCode:" + response.code() + " ResponseMessage:" + response.message()
                            }
                        }
                    } else {
                        result.messageDTO?.apply {
                            errNumber = Constants.COD_ERROR_RESPONSE_NULL
                            errMessage = Constants.MSG_ERROR_CONEXION
                        }
                    }
                } catch (e: Exception) {
                    result.messageDTO?.apply {
                        errNumber = Constants.COD_ERROR_EXCEPTION
                        errMessage = Constants.MSG_ERROR_CONEXION
                    }
                }
            } else {
                result.messageDTO?.apply {
                    errNumber = Constants.COD_ERROR_NOT_INTERNET
                    errMessage = Constants.MSG_ERROR_NOT_INTERNET
                }
            }
            return@withContext result
        }
    }

    suspend fun getUpdateAccounts(): DataResponseDTO<DataAccountModel> {
        return withContext(Dispatchers.IO) {
            val result = DataResponseDTO<DataAccountModel>()
            if (NetworkUtil.isThereInternetConnection(context)) {
                try {
                    val response = api.getUpdateAccounts()
                    if (response != null) {
                        val body = response.body()
                        if (body != null) {
                            result.data = body.data
                            result.code = body.code
                            result.messageDTO?.apply {
                                this.errNumber = body.code
                            }
                        } else {
                            result.messageDTO?.apply {
                                errNumber = Constants.COD_ERROR_BODY_NULL
                                errMessage =  Constants.MSG_ERROR_CONEXION+ "\n Motivo-> ResponseCode:" + response.code() + " ResponseMessage:" + response.message()
                            }
                        }
                    } else {
                        result.messageDTO?.apply {
                            errNumber = Constants.COD_ERROR_RESPONSE_NULL
                            errMessage = Constants.MSG_ERROR_CONEXION
                        }
                    }
                } catch (e: Exception) {
                    result.messageDTO?.apply {
                        errNumber = Constants.COD_ERROR_EXCEPTION
                        errMessage = Constants.MSG_ERROR_CONEXION
                    }
                }
            } else {
                result.messageDTO?.apply {
                    errNumber = Constants.COD_ERROR_NOT_INTERNET
                    errMessage = Constants.MSG_ERROR_NOT_INTERNET
                }
            }
            return@withContext result
        }
    }


}
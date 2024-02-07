package com.rgk.appbancamovil.data.source.remote.service

import android.content.Context
import com.rgk.appbancamovil.data.common.Constants
import com.rgk.appbancamovil.data.common.NetworkUtil
import com.rgk.appbancamovil.data.source.remote.model.DataMotionModel
import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.data.source.remote.network.AccountsDetailClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class AccountsDetailService @Inject constructor(
    private val api : AccountsDetailClient,
    @ApplicationContext private val context: Context
){
    suspend fun getMoves(id:Int): DataResponseDTO<DataMotionModel> {
        return withContext(Dispatchers.IO) {
            val result = DataResponseDTO<DataMotionModel>()
            if (NetworkUtil.isThereInternetConnection(context)) {
                try {
                    val response = api.getMoves(id)
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
                        errMessage = Constants.MSG_ERROR_CONEXION+e
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
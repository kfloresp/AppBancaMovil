package com.rgk.appbancamovil.feature.accounts_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.appbancamovil.data.common.Constants
import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.domain.GetMovesUseCase
import com.rgk.appbancamovil.domain.model.DataAccount
import com.rgk.appbancamovil.domain.model.DataMotion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AccountsDetailViewModel @Inject constructor(
    private val getMovesUseCase: GetMovesUseCase,
    @Named("DataAccount") val dataAccount: DataAccount,
) : ViewModel() {

    fun init() {
        this.resultDataAccount.postValue(dataAccount)
    }

    val resultDataAccount = MutableLiveData<DataAccount>()
    val loadMoves = MutableLiveData<Boolean>()
    val resultMoves = MutableLiveData<DataResponseDTO<DataMotion>>()
    val errorMoves = MutableLiveData<Boolean>()

    fun getMoves() {
        viewModelScope.launch(Dispatchers.IO) {
            loadMoves.postValue(true)
            val result = getMovesUseCase(dataAccount.id)
            if (result.code == Constants.ERROR_OK) {
                resultMoves.postValue(result)
            }else{
                errorMoves.postValue(true)
            }
            loadMoves.postValue(false)
        }
    }
}
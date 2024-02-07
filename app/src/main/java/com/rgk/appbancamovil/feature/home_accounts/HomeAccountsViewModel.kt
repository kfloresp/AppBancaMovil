package com.rgk.appbancamovil.feature.home_accounts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.appbancamovil.data.common.Constants.ERROR_OK
import com.rgk.appbancamovil.data.common.DataResponseDTO
import com.rgk.appbancamovil.domain.GetAccountsUseCase
import com.rgk.appbancamovil.domain.GetUpdateAccountsUseCase
import com.rgk.appbancamovil.domain.model.DataAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAccountsViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getUpdateAccountsUseCase: GetUpdateAccountsUseCase
) : ViewModel() {

    val loadAccounts = MutableLiveData<Boolean>()
    val errorAccounts = MutableLiveData<Boolean>()
    val resultAccounts = MutableLiveData<DataResponseDTO<DataAccount>>()

    val errorUpdateAccounts = MutableLiveData<Boolean>()
    val resultUpdateAccounts = MutableLiveData<DataResponseDTO<DataAccount>>()

    fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            loadAccounts.postValue(true)
            val result = getAccountsUseCase()
            if (result.code == ERROR_OK) {
                resultAccounts.postValue(result)
            }else{
                errorAccounts.postValue(true)
            }
            loadAccounts.postValue(false)
        }
    }

    fun getUpdateAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            loadAccounts.postValue(true)
            val result = getUpdateAccountsUseCase()
            if (result.code == ERROR_OK) {
                resultUpdateAccounts.postValue(result)
            }else{
                errorUpdateAccounts.postValue(true)
            }
            loadAccounts.postValue(false)
        }
    }
}
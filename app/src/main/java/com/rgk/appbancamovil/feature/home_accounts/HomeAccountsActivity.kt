package com.rgk.appbancamovil.feature.home_accounts

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgk.appbancamovil.R
import com.rgk.appbancamovil.databinding.ActivityHomeAccountsBinding
import com.rgk.appbancamovil.domain.model.DataAccount
import com.rgk.appbancamovil.feature.accounts_detail.AccountsDetailActivity
import com.rgk.appbancamovil.util.BaseRgkActivity
import com.rgk.appbancamovil.util.DialogUtil
import com.rgk.appbancamovil.util.SessionTimerService
import com.rgk.appbancamovil.util.extensions.setupCustomTitle
import com.rgk.appbancamovil.util.intentFor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAccountsActivity : BaseRgkActivity() {
    private lateinit var binding: ActivityHomeAccountsBinding
    private val viewModel: HomeAccountsViewModel by viewModels()
    private lateinit var adapter: HomeAccountsAdapter
    override fun showLoading() {
        binding.viewLoading.rltContent.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.viewLoading.rltContent.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAccountsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniToolbar()
        setupView()
        setupObservers()
        startSessionTimer()
    }

    private fun iniToolbar() {
        with(binding) {
            setSupportActionBar(toolbarInc.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            binding.toolbarInc.toolbar.setupCustomTitle(getString(R.string.title_products))
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            loadAccounts.observe(this@HomeAccountsActivity) { load ->
                if (load) {
                    showLoading()
                    binding.swipe.isRefreshing = true
                    binding.swipe.isEnabled = false
                } else {
                    hideLoading()
                    binding.swipe.isRefreshing = false
                    binding.swipe.isEnabled = true
                }
            }
            resultAccounts.observe(this@HomeAccountsActivity) { response ->
                response?.data?.let {
                    if (it.isEmpty()) {
                        showDialogRetryAccounts()
                    } else {
                        adapter.updateItems(it)
                    }
                } ?: run {
                    showDialogRetryAccounts()
                }
            }
            resultUpdateAccounts.observe(this@HomeAccountsActivity) { response ->
                response?.data?.let {
                    if (it.isEmpty()) {
                        showDialogAceptRetryAccounts()
                    } else {
                        adapter.updateItems(it)
                    }
                } ?: run {
                    showDialogAceptRetryAccounts()
                }
            }
            errorAccounts.observe(this@HomeAccountsActivity) { isError ->
                if (isError) {
                    binding.clEmpy.isVisible = isError
                    binding.swipe.isVisible = !isError
                } else {
                    binding.clEmpy.isVisible = isError
                    binding.swipe.isVisible = !isError
                }
            }
            errorUpdateAccounts.observe(this@HomeAccountsActivity) { isError ->
                if (isError) {
                    binding.clEmpy.isVisible = isError
                    binding.swipe.isVisible = !isError
                } else {
                    binding.clEmpy.isVisible = isError
                    binding.swipe.isVisible = !isError
                }
            }
        }
    }

    private fun showDialogAceptRetryAccounts() {
        DialogUtil.showAlertAcept(this@HomeAccountsActivity,getString(R.string.title_retry_accounts),getString(R.string.retry_accounts),object:
            DialogUtil.AlertAceptRetryAccountInterface{
            override fun onClickAceptRetryAccount() {
                adapter.setDataError(getString(R.string.error_retry))
            }
        })
    }

    private fun showDialogRetryAccounts() {
        DialogUtil.showAlertRetryAccount(this@HomeAccountsActivity,getString(R.string.title_retry_accounts),getString(R.string.retry_accounts),object:
            DialogUtil.AlertRetryAccountInterface {
            override fun onClickRetryAccount() {
                viewModel.getAccounts()
            }
        })
    }

    private fun setupView() {
        val linearLayoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        binding.rvData.layoutManager = linearLayoutManager
        adapter = HomeAccountsAdapter(::selectedAccount)
        binding.rvData.adapter = adapter
        binding.swipe.setOnRefreshListener {
            viewModel.getUpdateAccounts()
        }
        viewModel.getAccounts()
    }

    private fun selectedAccount(dataAccount: DataAccount) {
        startActivity(intentFor<AccountsDetailActivity> {
            putExtra(AccountsDetailActivity.CLAVE_ITEM, dataAccount)
        })
    }
}
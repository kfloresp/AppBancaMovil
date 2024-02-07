package com.rgk.appbancamovil.feature.accounts_detail

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgk.appbancamovil.R
import com.rgk.appbancamovil.databinding.ActivityAccountsDetailBinding
import com.rgk.appbancamovil.databinding.ActivityHomeAccountsBinding
import com.rgk.appbancamovil.feature.home_accounts.HomeAccountsAdapter
import com.rgk.appbancamovil.util.BaseRgkActivity
import com.rgk.appbancamovil.util.FormatMoneyUtil
import com.rgk.appbancamovil.util.FormatString
import com.rgk.appbancamovil.util.SessionTimerService
import com.rgk.appbancamovil.util.extensions.setSafeOnClickListener
import com.rgk.appbancamovil.util.extensions.setupCustomTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountsDetailActivity : BaseRgkActivity() {
    private lateinit var binding: ActivityAccountsDetailBinding
    private val viewModel: AccountsDetailViewModel by viewModels()
    private lateinit var adapter: AccountsDetailAdapter
    override fun showLoading() {
        binding.viewLoading.rltContent.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.viewLoading.rltContent.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniToolbar()
        setupView()
        setupObservers()
        startSessionTimer()
    }

    private fun setupObservers() {
        with(viewModel) {
            loadMoves.observe(this@AccountsDetailActivity) { load ->
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
            resultMoves.observe(this@AccountsDetailActivity) { response ->
                response?.data?.let {
                    if (it.isEmpty()) {
                        errorMoves.postValue(true)
                    } else {
                        adapter.updateItems(it)
                    }
                } ?: run {
                    errorMoves.postValue(true)
                }
            }
            errorMoves.observe(this@AccountsDetailActivity) { isError ->
                if (isError) {
                    binding.clEmpy.isVisible = isError
                    binding.swipe.isVisible = !isError
                } else {
                    binding.clEmpy.isVisible = isError
                    binding.swipe.isVisible = !isError
                }
            }
            resultDataAccount.observe(this@AccountsDetailActivity) { data ->
                with(binding) {
                    tvNameAccount.text = data.account
                    tvAmount.text = FormatMoneyUtil.formatMoney(data.balanceAmount)
                    tvMoney.text = data.currency
                    tvNumberAccountData.text = data.accountNumber
                }
            }
        }
    }

    private fun setupView() {
        with(binding) {
            val linearLayoutManager =
                LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
            binding.rvData.layoutManager = linearLayoutManager
            adapter = AccountsDetailAdapter()
            binding.rvData.adapter = adapter
            binding.swipe.setOnRefreshListener {
                viewModel.getMoves()
            }
            btnCopy.setSafeOnClickListener {
                FormatString.copyToClipboard(
                    this@AccountsDetailActivity,
                    viewModel.dataAccount.accountNumber.orEmpty()
                )
            }
            viewModel.init()
            viewModel.getMoves()
        }
    }

    private fun iniToolbar() {
        with(binding) {
            setSupportActionBar(toolbarInc.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            binding.toolbarInc.toolbar.setupCustomTitle(getString(R.string.detail_account))
        }
    }

    companion object {
        val CLAVE_ITEM: String = "AccountDetail.account"
    }
}
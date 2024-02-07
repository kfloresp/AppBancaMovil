package com.rgk.appbancamovil.feature.home_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.rgk.appbancamovil.databinding.ActivityHomeLoginBinding
import com.rgk.appbancamovil.feature.home_accounts.HomeAccountsActivity
import com.rgk.appbancamovil.util.BaseRgkActivity
import com.rgk.appbancamovil.util.extensions.afterTextChanged
import com.rgk.appbancamovil.util.extensions.applyAlphanumericFilter
import com.rgk.appbancamovil.util.extensions.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class HomeLoginActivity : BaseRgkActivity() {
    private lateinit var binding: ActivityHomeLoginBinding
    private val viewModel: HomeLoginViewModel by viewModels()

    override fun showLoading() {
        binding.viewLoading.rltContent.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.viewLoading.rltContent.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupObservers()
        startSessionTimer()
    }

    private fun setupObservers() {
        with(viewModel) {
            loadLoginMobile.observe(this@HomeLoginActivity) { load ->
                if (load) showLoading() else hideLoading()
            }
            resultLoginMobile.observe(this@HomeLoginActivity) { result ->
                result?.let {
                    goToHomeAccounts()
                }
            }
            errorLoginMobile.observe(this@HomeLoginActivity) { result ->
                result?.let {
                    Toasty.error(baseContext,result.errMessage.orEmpty()).show()
                }
            }
        }
    }

    private fun goToHomeAccounts() {
        val i = Intent(this, HomeAccountsActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(i)
        finishAffinity()
    }

    private fun setupView() {
        with(binding) {
            tieUsuario.applyAlphanumericFilter()
            tiePassword.applyAlphanumericFilter()
            btnLogin.setSafeOnClickListener {
                toVerify()
            }
            tieUsuario.afterTextChanged {
                btnLogin.isEnabled =
                    it.isNotEmpty() &&
                            tiePassword.text.toString().isNotEmpty()
            }
            tiePassword.afterTextChanged {
                btnLogin.isEnabled =
                    it.isNotEmpty() &&
                            tieUsuario.text.toString().isNotEmpty()
            }
        }
    }

    private fun toVerify() {
        hideKeyboard()
        with(binding) {
            viewModel.loginMobile(
                tieUsuario.text.toString().trim(),
                tiePassword.text.toString().trim()
            )
        }
    }
}
package com.rgk.appbancamovil.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.rgk.appbancamovil.data.common.NetworkUtil
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.rgk.appbancamovil.feature.home_accounts.HomeAccountsActivity
import com.rgk.appbancamovil.feature.home_login.HomeLoginActivity

abstract class BaseRgkActivity: AppCompatActivity() {

    private val handler = Handler()
    private val sessionTimeoutMillis: Long = Constants.timeLogout
    private lateinit var sessionExpirationReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionExpirationReceiver = createSessionExpirationReceiver()
        registerReceiver(sessionExpirationReceiver, IntentFilter("SESSION_EXPIRED"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(sessionExpirationReceiver)
        stopSessionTimer()
    }

    private fun createSessionExpirationReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                showSessionExpiredDialog()
            }
        }
    }

    private val sessionTimeoutRunnable = Runnable {
        showSessionExpiredDialog()
    }

    private fun resetSessionTimer() {
        handler.removeCallbacks(sessionTimeoutRunnable)
        handler.postDelayed(sessionTimeoutRunnable, sessionTimeoutMillis)
    }

    private fun stopSessionTimer() {
        handler.removeCallbacks(sessionTimeoutRunnable)
    }

    protected fun startSessionTimer() {
        resetSessionTimer()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        resetSessionTimer()
    }

    private fun showSessionExpiredDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Sesión Expirada")
        alertDialogBuilder.setMessage("Tu sesión ha expirado. Por favor, inicia sesión nuevamente.")
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, which ->
            redirectToLogin()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun redirectToLogin() {
        val intent = Intent(this, HomeLoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finishAffinity()
    }

    fun context(): Context {
        return baseContext
    }

    fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    protected abstract fun showLoading()
    protected abstract fun hideLoading()

}
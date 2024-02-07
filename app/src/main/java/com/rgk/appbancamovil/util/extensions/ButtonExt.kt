package com.rgk.appbancamovil.util.extensions

import android.os.SystemClock
import android.view.View

fun View.setSafeOnClickListener(
    safeInterval: Long = 1000L,
    onSafeClick: (View) -> Unit
) {
    val safeClickListener = SafeOnClickListener(onSafeClick, safeInterval)
    setOnClickListener(safeClickListener)
}

class SafeOnClickListener(
    private val onSafeClick: (View) -> Unit,
    private val safeInterval: Long = 1000L
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(view: View) {
        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastTimeClicked >= safeInterval) {
            lastTimeClicked = currentTime
            onSafeClick(view)
        }
    }
}
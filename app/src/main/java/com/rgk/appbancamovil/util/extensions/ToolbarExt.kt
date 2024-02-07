package com.rgk.appbancamovil.util.extensions

import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.TextViewCompat
import com.rgk.appbancamovil.R

fun Toolbar.setupCustomTitle(title: String) {
    val customTitleTextView = this.findViewWithTag<TextView>("customTitleTextView")

    if (customTitleTextView != null) {
        customTitleTextView.text = title
    } else {
        val newCustomTitleTextView = TextView(this.context).apply {
            text = title
            TextViewCompat.setTextAppearance(this, R.style.ToolbarTextAppearance)
            tag = "customTitleTextView"
        }

        val layoutParams = Toolbar.LayoutParams(
            Toolbar.LayoutParams.WRAP_CONTENT,
            Toolbar.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }

        this.addView(newCustomTitleTextView, layoutParams)
    }
}

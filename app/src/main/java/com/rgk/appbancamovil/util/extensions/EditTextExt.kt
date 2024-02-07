package com.rgk.appbancamovil.util.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.applyAlphanumericFilter() {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            val text = editable.toString()
            val alphanumericPattern = Regex("^[a-zA-Z\\d@&_]*$")

            if (!alphanumericPattern.matches(text)) {
                val newText = text.replace(Regex("[^a-zA-Z\\d@&_]+"), "")
                if (newText != text) {
                    setText(newText)
                    setSelection(newText.length)
                }
            }
        }
    })
}
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}
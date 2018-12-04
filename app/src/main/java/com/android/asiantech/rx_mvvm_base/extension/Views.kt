package com.android.asiantech.rx_mvvm_base.extension

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/10/18.
 */
fun EditText.onTextChangeListener(afterTextChanged: (Editable?) -> Unit = {}) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0)
        }
    })
}

fun View.hideKeyboard(context: Context) {
    val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(this.windowToken, 0)
}

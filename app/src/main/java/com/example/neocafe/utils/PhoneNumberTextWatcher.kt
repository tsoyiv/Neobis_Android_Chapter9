package com.example.neocafe.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PhoneNumberTextWatcher(private val editText: EditText) : TextWatcher {
    private var isFormatting = false
    private var isDeleting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
        if (isFormatting || isDeleting) {
            return
        }
        isFormatting = true
        val rawText = editable.toString().replace("[^\\d]".toRegex(), "")

        val formattedText = StringBuilder()
        var digitIndex = 0

        for (i in 0 until rawText.length) {
            val currentChar = rawText[i]

            if (digitIndex >= PHONE_NUMBER_LENGTH) {
                break
            }

            if (i == 0) {
                formattedText.append("+")
                formattedText.append(currentChar)
            } else {
                if (digitIndex == 2 || digitIndex == 5 || digitIndex == 7 || digitIndex == 9) {
                    formattedText.append(' ')
                }
                formattedText.append(currentChar)
                digitIndex++
            }
        }
        editText.setText(formattedText.toString())
        editText.setSelection(formattedText.length)
        isFormatting = false
    }

    companion object {
        private const val PHONE_NUMBER_LENGTH = 11
    }
}

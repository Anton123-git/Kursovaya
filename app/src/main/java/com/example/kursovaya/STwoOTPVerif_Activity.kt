package com.example.kursovaya

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class STwoOTPVerif_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_otpverif)

        val editTexts = listOf(
            findViewById<EditText>(R.id.editTextText5),
            findViewById<EditText>(R.id.editTextText6),
            findViewById<EditText>(R.id.editTextText7),
            findViewById<EditText>(R.id.editTextText8),
            findViewById<EditText>(R.id.editTextText9),
            findViewById<EditText>(R.id.editTextText10)
        )

        val button = findViewById<Button>(R.id.button)
        val buttonBackgroundFilled = R.drawable.btn_next_style
        val buttonBackgroundEmpty = R.drawable.btn_signup_gray

        button.setOnClickListener {
            if (checkFieldsAreFilled(editTexts)) {
                val intent = Intent(this, STwoNewPass::class.java)
                startActivity(intent)
            }
        }

        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        editText.setBackgroundResource(R.drawable.inputstyle2)
                        val index = editTexts.indexOf(editText)
                        if (index < editTexts.size - 1) {
                            editTexts[index + 1].requestFocus()
                        }
                    } else {
                        editText.setBackgroundResource(R.drawable.inputstyle_empty)
                    }
                    checkFieldsAndEnableButton(editTexts, button, buttonBackgroundFilled, buttonBackgroundEmpty)
                }
            })
        }

        checkFieldsAndEnableButton(editTexts, button, buttonBackgroundFilled, buttonBackgroundEmpty)
    }

    private fun checkFieldsAndEnableButton(editTexts: List<EditText>, button: Button, backgroundFilled: Int, backgroundEmpty: Int) {
        button.isEnabled = checkFieldsAreFilled(editTexts)
        button.background = resources.getDrawable(if (button.isEnabled) backgroundFilled else backgroundEmpty, null)
    }

    private fun checkFieldsAreFilled(editTexts: List<EditText>): Boolean {
        return editTexts.all { it.text.isNotEmpty() }
    }
}

package com.mortarifabio.desafiowebservices.authentication

import android.app.Application
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.mortarifabio.desafiowebservices.R
import com.mortarifabio.desafiowebservices.databinding.ActivityLoginBinding
import com.mortarifabio.desafiowebservices.databinding.ActivityRegisterBinding
import com.mortarifabio.desafiowebservices.utils.Constants.Authentication.SHARED_PREFERENCES_EMAIL_KEY
import com.mortarifabio.desafiowebservices.utils.Constants.Authentication.SHARED_PREFERENCES_FILENAME

class AuthenticationBusiness(
    private val context: Application
) {

    private val sharedPref by lazy {
        context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, AppCompatActivity.MODE_PRIVATE)
    }

    fun login(binding: ActivityLoginBinding): Boolean {
        binding.apply {
            return if(isEditTextFilled(
                    tietLoginEmail,
                    tilLoginEmail,
                    context.getString(R.string.email)
                ) && isEmailValid(
                    tietLoginEmail,
                    tilLoginEmail
                ) && isEditTextFilled(
                    tietLoginPassword,
                    tilLoginPassword,
                    context.getString(R.string.password)
                ) && isPasswordValid(
                    tietLoginPassword,
                    tilLoginPassword
                )
            ) {
                if(cbLoginRemember.isChecked){
                    sharedPref.edit()
                        .putString(SHARED_PREFERENCES_EMAIL_KEY, tietLoginEmail.text.toString())
                        .apply()
                }
                true
            } else {
                false
            }
        }
    }

    fun register(binding: ActivityRegisterBinding): Boolean {
        binding.apply {
            return isEditTextFilled(
                tietRegisterName,
                tilRegisterName,
                context.getString(R.string.name)
            ) && isEditTextFilled(
                tietRegisterEmail,
                tilRegisterEmail,
                context.getString(R.string.email)
            ) && isEmailValid(
                tietRegisterEmail,
                tilRegisterEmail
            ) && isEditTextFilled(
                tietRegisterPassword,
                tilRegisterPassword,
                context.getString(R.string.password)
            ) && isPasswordValid(
                tietRegisterPassword,
                tilRegisterPassword
            )
        }
    }

    fun getUserEmail(): String {
        val userEmail = sharedPref.getString(SHARED_PREFERENCES_EMAIL_KEY, "")
        return userEmail?.let {
            userEmail
        } ?: ""
    }

    private fun isEditTextFilled(et: EditText, til: TextInputLayout, label: String): Boolean {
        return if (et.text.isNotEmpty()) {
            til.isErrorEnabled = false
            true
        } else {
            til.error = context.getString(R.string.field_required, label)
            et.requestFocus()
            false
        }
    }

    private fun isEmailValid(et: EditText, til: TextInputLayout): Boolean {
        return if (EMAIL_ADDRESS.matcher(et.text.toString()).matches()) {
            til.isErrorEnabled = false
            true
        } else {
            if (et.text.isEmpty()) {
                til.error = context.getString(
                    R.string.field_required,
                    context.getString(R.string.email)
                )
            } else {
                til.error = context.getString(R.string.msg_invalid_email)
            }
            et.requestFocus()
            false
        }
    }

    private fun isPasswordValid(et: EditText, til: TextInputLayout): Boolean {
        return if (et.text.matches(Regex("^(?=.*[a-zA-Z])(?=.*[0-9]).{6,}$"))) {
            til.isErrorEnabled = false
            true
        } else {
            til.error = context.getString(R.string.password_invalid)
            et.requestFocus()
            false
        }
    }
}
package com.mortarifabio.desafiowebservices.authentication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mortarifabio.desafiowebservices.authentication.AuthenticationBusiness
import com.mortarifabio.desafiowebservices.databinding.ActivityLoginBinding
import com.mortarifabio.desafiowebservices.databinding.ActivityRegisterBinding

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    private val business: AuthenticationBusiness by lazy {
        AuthenticationBusiness(getApplication())
    }

    fun login(binding: ActivityLoginBinding): Boolean {
        return business.login(binding)
    }

    fun register(binding: ActivityRegisterBinding): Boolean {
        return business.register(binding)
    }

    fun getUserEmail(): String {
        return business.getUserEmail()
    }
}
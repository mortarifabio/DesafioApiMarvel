package com.mortarifabio.desafiowebservices.authentication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mortarifabio.desafiowebservices.authentication.viewModel.AuthenticationViewModel
import com.mortarifabio.desafiowebservices.comics.view.ComicsActivity
import com.mortarifabio.desafiowebservices.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(AuthenticationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadPreferences()
        setupObservables()
    }

    private fun loadPreferences() {
        binding.tietLoginEmail.setText(viewModel.getUserEmail())
    }

    private fun setupObservables() = with(binding) {
        btLoginLogin.setOnClickListener {
            if(viewModel.login(this)){
                val intent = Intent(this@LoginActivity, ComicsActivity::class.java)
                startActivity(intent)
            }
        }
        btLoginCreate.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
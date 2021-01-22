package com.mortarifabio.desafiowebservices.authentication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mortarifabio.desafiowebservices.authentication.viewModel.AuthenticationViewModel
import com.mortarifabio.desafiowebservices.comics.view.ComicsActivity
import com.mortarifabio.desafiowebservices.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(AuthenticationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservables()
    }

    private fun setupObservables() = with(binding) {
        mtRegister.setNavigationOnClickListener {
            finish()
        }
        btRegisterSave.setOnClickListener {
            if(viewModel.register(this)) {
                val intent = Intent(this@RegisterActivity, ComicsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
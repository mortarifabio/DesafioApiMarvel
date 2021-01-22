package com.mortarifabio.desafiowebservices.authentication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mortarifabio.desafiowebservices.databinding.ActivitySplashBinding
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTimer()
    }

    private fun setTimer() {
        Timer("SplashScreen", false).schedule(3000){
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}
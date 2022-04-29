package com.jessica.yourfavoritemovies.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jessica.yourfavoritemovies.authentication.view.LoginActivity
import com.jessica.yourfavoritemovies.databinding.ActivitySplashBinding
import java.util.*

class SplashActivity : AppCompatActivity() {
    private val timer = Timer()

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        timer.schedule(object : TimerTask() {
            override fun run() {
                jump()
            }
        }, 3000)
    }

    private fun jump() {
        timer.cancel()
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()

    }
}
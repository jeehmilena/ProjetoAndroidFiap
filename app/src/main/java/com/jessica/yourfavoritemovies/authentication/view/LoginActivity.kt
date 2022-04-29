package com.jessica.yourfavoritemovies.authentication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jessica.yourfavoritemovies.util.MovieUtil
import com.jessica.yourfavoritemovies.R
import com.jessica.yourfavoritemovies.authentication.viewmodel.AuthenticationViewModel
import com.jessica.yourfavoritemovies.databinding.ActivityLoginBinding
import com.jessica.yourfavoritemovies.home.view.HomeActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this)[AuthenticationViewModel::class.java]
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btLogin.setOnClickListener {
            val email = binding.etvEmail.text.toString()
            val password = binding.etvPassword.text.toString()

            when {
                MovieUtil.validateEmailPassword(email, password) -> {
                    viewModel.loginEmailPassword(email, password)
                }
                else -> {
                    Snackbar.make(
                        binding.btLogin,
                        resources.getString(R.string.login_failed),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.tvLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.stateLogin.observe(this, Observer { state ->
            state?.let {
                navigateToHome(it)
            }
        })

        viewModel.error.observe(this, Observer { loading ->
            loading?.let {
                showErrorMessage(it)
            }
        })
    }

    private fun navigateToHome(status: Boolean) {
        when {
            status -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.btLogin, message, Snackbar.LENGTH_LONG).show()
    }
}
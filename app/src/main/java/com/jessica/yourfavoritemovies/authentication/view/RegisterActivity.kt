package com.jessica.yourfavoritemovies.authentication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jessica.yourfavoritemovies.util.MovieUtil.validateNameEmailPassword
import com.jessica.yourfavoritemovies.authentication.viewmodel.AuthenticationViewModel
import com.jessica.yourfavoritemovies.databinding.ActivityRegisterBinding
import com.jessica.yourfavoritemovies.home.view.HomeActivity

class RegisterActivity : AppCompatActivity() {
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this)[AuthenticationViewModel::class.java]
    }

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRegister.setOnClickListener {
            val name = binding.etvNameRegister.text.toString()
            val email = binding.etvEmailRegister.text.toString()
            val password = binding.etvPasswordRegister.text.toString()

            when {
                validateNameEmailPassword(name, email, password) -> {
                    viewModel.registerUser(email, password)
                }
            }

            initViewModel()
        }
    }

    private fun initViewModel() {

        viewModel.stateRegister.observe(this, Observer { state ->
            state?.let {
                navigateToHome(it)
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                showLoading(it)
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
        Snackbar.make(binding.btnRegister, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showLoading(status: Boolean) {
        when {
            status -> {
                binding.pbRegister.visibility = View.VISIBLE
            }
            else -> {
                binding.pbRegister.visibility = View.GONE
            }
        }
    }
}
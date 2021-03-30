package com.login.loginapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.login.loginapp.Error
import com.login.loginapp.R
import com.login.loginapp.data.ApiResponse
import com.login.loginapp.databinding.ActivityLoginBinding
import com.login.loginapp.viewmodel.LoginCallBack
import com.login.loginapp.viewmodel.LoginViewModel
import com.login.loginapp.viewmodel.LoginViewModelFactory

class LogInActivity : AppCompatActivity(), LoginCallBack {
    lateinit var activityMainBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityMainBinding.viewModel =
            ViewModelProvider(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)
    }


    override fun onResponse(apiResponse: ApiResponse<Int?>) {
        Snackbar.make(activityMainBinding.root, message(apiResponse), Snackbar.LENGTH_SHORT).show()
    }

    override fun onError(error: Error) {
        Snackbar.make(activityMainBinding.root, errorHandling(error), Snackbar.LENGTH_SHORT).show()
    }


    private fun message(api: ApiResponse<Int?>): Int {
        return if (api.result != null && api.result == 1) {
            R.string.msg_login_success
        } else
            when (api.error) {
                Error.UNKNOWN_ERROR -> {
                    R.string.error_unknown
                }
                Error.INTERNAL_SERVER_ERROR -> {
                    R.string.error_internal_server
                }
                Error.WRONG_CREDENTIALS -> {
                    R.string.error_wrong_credentials
                }
                else -> {
                    R.string.error_unknown
                }

            }
    }

    private fun errorHandling(error: Error): Int {
        return when (error) {
            Error.EMPTY_EMAIL -> {
                R.string.empty_email
            }
            Error.EMPTY_PASSWORD -> {
                R.string.empty_password
            }
            Error.INTERNAL_SERVER_ERROR -> {
                R.string.error_internal_server
            }
            else -> {
                R.string.error_unknown
            }
        }

    }
}
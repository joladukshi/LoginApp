package com.login.loginapp

import com.login.loginapp.viewmodel.LoginViewModel
import org.junit.Before
import org.junit.Test
import com.login.loginapp.viewmodel.LoginCallBack
import org.junit.Assert.*

class EmailValidator {
    lateinit var viewModel: LoginViewModel

    @Before
    fun initialize(){
        viewModel = LoginViewModel(null)
    }

    @Test
    fun emailValidator() {
        assertTrue(viewModel.isEmpty(""))
    }
}
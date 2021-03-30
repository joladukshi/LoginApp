package com.login.loginapp.data

import com.login.loginapp.Error

data class ApiResponse<T>(
    val result : T?,
    val error : Error?
)

package com.login.loginapp.viewmodel

import com.login.loginapp.Error
import com.login.loginapp.data.ApiResponse

interface LoginCallBack {

    fun onResponse(apiResponse: ApiResponse<Int?>)

    fun onError(error: Error)

}
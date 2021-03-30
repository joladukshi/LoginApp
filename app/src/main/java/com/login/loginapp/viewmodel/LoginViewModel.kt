package com.login.loginapp.viewmodel

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import com.login.loginapp.Error
import com.login.loginapp.data.ApiResponse
import com.login.loginapp.data.ApiService
import com.login.loginapp.model.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val listener: LoginCallBack?) : ViewModel() {

    private val user: User = User("", "")
    private val compositeDisposable = CompositeDisposable()

    fun isEmpty(field: String): Boolean {
        return TextUtils.isEmpty(field)
    }

    fun onLoginClicked() {
        when {
            isEmpty(user.getEmail()) -> listener?.onError(Error.EMPTY_EMAIL)
            isEmpty(user.getPassword()) -> listener?.onError(Error.EMPTY_PASSWORD)
            else
                //suppose we are making an ApiCall and need to put it on the background
            -> compositeDisposable.addAll(
                Single.just(tryToLogin(user.getEmail(), user.getPassword()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ value ->
                        listener?.onResponse(value)
                    }, {
                        listener?.onError(Error.INTERNAL_SERVER_ERROR)
                    })
            )
        }
    }

    private fun tryToLogin(email: String, password: String): ApiResponse<Int?> {
        val apiService = ApiService()
        return apiService.login(email, password)
    }

    val emailTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                user.setEmail(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

    val passwordTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                user.setPassword(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}
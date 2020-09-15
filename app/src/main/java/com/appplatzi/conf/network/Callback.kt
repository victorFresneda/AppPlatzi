package com.appplatzi.conf.network

import java.lang.Exception

interface Callback<T> {
    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)

}
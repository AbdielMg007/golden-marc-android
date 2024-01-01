package com.mg.goldenmarc.common

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure<U>(val exception: Throwable) : Result<Nothing>()
}
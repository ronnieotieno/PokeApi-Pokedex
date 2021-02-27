package dev.ronnie.pokeapiandroidtask.utils

import okhttp3.ResponseBody

sealed class NetworkResource<out T> {
    data class Success<out T>(val value: T) : NetworkResource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : NetworkResource<Nothing>()

    object Loading : NetworkResource<Nothing>()
}
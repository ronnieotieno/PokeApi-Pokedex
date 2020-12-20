package dev.ronnie.pokeapiandroidtask.utils

import okhttp3.ResponseBody

/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}
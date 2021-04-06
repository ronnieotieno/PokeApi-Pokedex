package dev.ronnie.pokeapiandroidtask.data.repositories

import dev.ronnie.pokeapiandroidtask.utils.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException


/**
 *created by Ronnie Otieno on 13-Feb-21.
 **/
open class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        NetworkResource.Failure(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    }
                    else -> {
                        NetworkResource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}
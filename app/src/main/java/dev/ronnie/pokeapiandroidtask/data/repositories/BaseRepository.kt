package dev.ronnie.pokeapiandroidtask.data.repositories

import dev.ronnie.pokeapiandroidtask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException


/**
 *created by Ronnie Otieno on 13-Feb-21.
 **/
open class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {

                        Resource.Failure(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )

                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}
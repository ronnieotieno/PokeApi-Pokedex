package dev.ronnie.pokeapiandroidtask.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.ronnie.pokeapiandroidtask.api.PokemonApi
import dev.ronnie.pokeapiandroidtask.data.datasource.PokemonDataSource
import dev.ronnie.pokeapiandroidtask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton


/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

@Singleton
class PokemonRepository @Inject constructor(private val pokemonApi: PokemonApi) {

    fun getPokemon(searchString: String?) = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 10),
        pagingSourceFactory = {
            PokemonDataSource(pokemonApi, searchString)
        }
    ).flow

    suspend fun getStats(id: Int) = safeApiCall {
        pokemonApi.getSinglePokemon(id)
    }

    private suspend fun <T> safeApiCall(
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
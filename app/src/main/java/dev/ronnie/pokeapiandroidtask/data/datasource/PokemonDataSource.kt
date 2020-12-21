package dev.ronnie.pokeapiandroidtask.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import dev.ronnie.pokeapiandroidtask.api.PokemonApi
import dev.ronnie.pokeapiandroidtask.domain.PokemonResult
import dev.ronnie.pokeapiandroidtask.domain.SinglePokemonResponse
import dev.ronnie.pokeapiandroidtask.utils.SEARCH_LOAD_SIZE
import dev.ronnie.pokeapiandroidtask.utils.STARTING_OFFSET_INDEX
import java.io.IOException


/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

/**
 * Paging 3 library which is under Android Jetpack. Used to paginate data. Here I am paginating the data using the pokeapi pagination
 */
class PokemonDataSource(private val pokemonApi: PokemonApi, private val searchString: String?) :
    PagingSource<Int, PokemonResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        val offset = params.key ?: STARTING_OFFSET_INDEX

        /*since pokeapi doesn't provide the search filter endpoint, here I am searching all of the 1118 pokemon based on the string user
        entered, the process will be repeated until all the 1118 pokemon in the api is searched, not the optimum solution, would be better if
        pokeapi had search endpoint.
        */

        /*if user doesn't pass search string the load size per scroll will  be 10 but if they provide it will be 100 as not everything will be displayed
        only that matches the search string
         */
        val loadSize = if (searchString == null) params.loadSize else SEARCH_LOAD_SIZE
        return try {
            val data = pokemonApi.getPokemons(loadSize, offset)

            //if search string isn't null I will filter the pokemon based on what user searched.
            val filteredData = if (searchString != null) {
                data.results.filter { it.name.contains(searchString, true) }
            } else {
                data.results
            }

            /* After getting the pokemon after calling endpoint pokemon, I am extracting the pokemon id and search individual pokemon data
        using  pokemon/{id}/ endpoint then adding the results to the pokemon result data class
        */
            filteredData.forEach {
                //example url https://pokeapi.co/api/v2/pokemon/1/
                val id = it.url.substringAfter("pokemon").replace("/", "").toInt()
                val singlePokemonResponse = getSinglePokemon(id, pokemonApi)
                it.singlePokemonResponse = singlePokemonResponse
            }

            data.next?.let { Log.d("NextOffset", it) }
            LoadResult.Page(
                data = filteredData,
                prevKey = if (offset == STARTING_OFFSET_INDEX) null else offset - loadSize,
                nextKey = if (data.next == null) null else offset + loadSize
            )
        } catch (t: Throwable) {
            var exception = t

            if (t is IOException) {
                exception = IOException("Please check internet connection")
            }
            LoadResult.Error(exception)
        }
    }

    private suspend fun getSinglePokemon(id: Int, pokemonApi: PokemonApi): SinglePokemonResponse? {
        return try {
            pokemonApi.getSinglePokemon(id)
        } catch (t: Throwable) {
            null
        }
    }
}
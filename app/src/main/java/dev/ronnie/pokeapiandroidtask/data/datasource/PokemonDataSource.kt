package dev.ronnie.pokeapiandroidtask.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.ronnie.pokeapiandroidtask.api.PokemonApi
import dev.ronnie.pokeapiandroidtask.model.PokemonResult
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

            //if search string isn't null filter the pokemon based on what user searched.
            val filteredData = if (searchString != null) {
                data.results.filter { it.name.contains(searchString, true) }
            } else {
                data.results
            }

            //next offset = last offset + loadsize, returning null is telling the paging 3 that there is no more to load and should stop
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


    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {

        return state.anchorPosition

    }
}
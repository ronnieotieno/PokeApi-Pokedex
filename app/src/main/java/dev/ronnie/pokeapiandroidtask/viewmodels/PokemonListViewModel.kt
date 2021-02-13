package dev.ronnie.pokeapiandroidtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ronnie.pokeapiandroidtask.data.repositories.DataStoreRepository
import dev.ronnie.pokeapiandroidtask.data.repositories.PokemonRepository
import dev.ronnie.pokeapiandroidtask.model.PokemonResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    dataStoreRepository: DataStoreRepository
) :
    ViewModel() {

    val isDialogShown = dataStoreRepository.isDialogShownFlow

    private var currentResult: Flow<PagingData<PokemonResult>>? = null
    fun getPokemons(searchString: String?): Flow<PagingData<PokemonResult>> {
        val newResult: Flow<PagingData<PokemonResult>> =
            pokemonRepository.getPokemon(searchString).cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}
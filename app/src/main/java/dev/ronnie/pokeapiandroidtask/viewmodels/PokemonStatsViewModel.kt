package dev.ronnie.pokeapiandroidtask.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ronnie.pokeapiandroidtask.data.repositories.PokemonRepository
import dev.ronnie.pokeapiandroidtask.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 *created by Ronnie Otieno on 13-Feb-21.
 **/

@HiltViewModel
class PokemonStatsViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    suspend fun getSinglePokemon(id: Int) = flow {
        emit(Resource.Loading)
        emit(pokemonRepository.getSinglePokemon(id))
    }

}
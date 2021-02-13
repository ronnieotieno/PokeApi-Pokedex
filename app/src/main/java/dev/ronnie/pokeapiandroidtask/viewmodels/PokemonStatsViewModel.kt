package dev.ronnie.pokeapiandroidtask.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ronnie.pokeapiandroidtask.data.repositories.PokemonRepository
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


/**
 *created by Ronnie Otieno on 13-Feb-21.
 **/

@HiltViewModel
class PokemonStatsViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    suspend fun getSinglePokemon(id: Int) = flowOf(pokemonRepository.getSinglePokemon(id))

}
package dev.ronnie.pokeapiandroidtask.domain

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)
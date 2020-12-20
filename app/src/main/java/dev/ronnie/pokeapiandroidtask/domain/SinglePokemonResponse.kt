package dev.ronnie.pokeapiandroidtask.domain

data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>
)
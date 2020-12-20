package dev.ronnie.pokeapiandroidtask.domain

data class PokemonResult(
    val name: String,
    val url: String,
    var singlePokemonResponse: SinglePokemonResponse?
)
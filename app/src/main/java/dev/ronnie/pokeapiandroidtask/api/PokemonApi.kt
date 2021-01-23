package dev.ronnie.pokeapiandroidtask.api

import dev.ronnie.pokeapiandroidtask.model.PokemonResponse
import dev.ronnie.pokeapiandroidtask.model.SinglePokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

interface PokemonApi {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): PokemonResponse

    @GET("pokemon/{id}/")
    suspend fun getSinglePokemon(
        @Path("id") id: Int
    ): SinglePokemonResponse
}
package dev.ronnie.pokeapiandroidtask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonResult(
    val name: String,
    val url: String,
    var singlePokemonResponse: SinglePokemonResponse?
) : Parcelable
package dev.ronnie.pokeapiandroidtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stat(
    val name: String,
    val url: String
) : Parcelable
package dev.ronnie.pokeapiandroidtask.utils

import android.content.Context
import android.widget.Toast


/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
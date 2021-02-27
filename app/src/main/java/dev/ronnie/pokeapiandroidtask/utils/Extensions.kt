package dev.ronnie.pokeapiandroidtask.utils

import android.content.Context
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible


/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.extractId() = this.substringAfter("pokemon").replace("/", "").toInt()

fun String.getPicUrl(): String {
    val id = this.extractId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
}

fun View.toggle(show: Boolean) {
    val transition: Transition = Slide(Gravity.BOTTOM)
    transition.duration = 200
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup?, transition)
    this.isVisible = show
}
package dev.ronnie.pokeapiandroidtask.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.ronnie.pokeapiandroidtask.R
import dev.ronnie.pokeapiandroidtask.adapters.StatsAdapter
import dev.ronnie.pokeapiandroidtask.databinding.FragmentPokemonStatsBinding
import dev.ronnie.pokeapiandroidtask.domain.Stats


/**
 *created by Ronnie Otieno on 21-Dec-20.
 **/
class PokemonStatsFragment : Fragment(R.layout.fragment_pokemon_stats) {

    private lateinit var binding: FragmentPokemonStatsBinding
    private val adapter = StatsAdapter()
    private val args = PokemonStatsFragmentArgs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPokemonStatsBinding.bind(view)
        val argument = arguments?.let { args.fromBundle(it) }
        val pokemonResult = argument?.pokemonResult!!
        val dominantColor = argument.dominantColor

        //setting the colors based on dominant colors
        if (dominantColor != 0) {
            binding.card.setBackgroundColor(dominantColor)
            binding.toolbar.setBackgroundColor(dominantColor)
            requireActivity().window.statusBarColor = dominantColor
        }

        val toolbar = binding.toolbar as Toolbar
        toolbar.elevation = 0.0F
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.title = pokemonResult.name
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setHomeButtonEnabled(true)

        toolbar.setNavigationOnClickListener {
            binding.root.findNavController().navigateUp()
        }

        binding.apply {
            (pokemonResult.singlePokemonResponse?.weight?.div(10.0).toString() + " metres").also {
                pokemonItemWeight.text = it
            }
            (pokemonResult.singlePokemonResponse?.height?.div(10.0).toString() + " kgs").also {
                pokemonItemHeight.text = it
            }

            pokemonStatList.adapter = adapter
            adapter.setStats(pokemonResult.singlePokemonResponse?.stats as ArrayList<Stats>)

            Glide.with(root)
                .load(pokemonResult.singlePokemonResponse?.sprites?.front_default)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(pokemonItemImage)
        }
    }
}
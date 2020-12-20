package dev.ronnie.pokeapiandroidtask.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dev.ronnie.pokeapiandroidtask.databinding.ListItemPokemonBinding
import dev.ronnie.pokeapiandroidtask.domain.PokemonResult
import dev.ronnie.pokeapiandroidtask.utils.NETWORK_VIEW_TYPE
import dev.ronnie.pokeapiandroidtask.utils.PRODUCT_VIEW_TYPE

/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

class PokemonAdapter() :
    PagingDataAdapter<PokemonResult, PokemonAdapter.ViewHolder>(
        PlayersDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = getItem(position)!!

        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    class ViewHolder(
        private val binding: ListItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemonResult: PokemonResult) {

            binding.apply {
                pokemonItemTitle.text = pokemonResult.name
                loadImage(this, pokemonResult)
            }

        }

        private fun loadImage(binding: ListItemPokemonBinding, pokemonResult: PokemonResult) {

            binding.apply {
                Glide.with(root)
                    .load(pokemonResult.singlePokemonResponse?.sprites?.front_default)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressCircular.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {

                            progressCircular.isVisible = false
                            return false
                        }

                    })
                    .into(pokemonItemImage)

            }
        }
    }

    private class PlayersDiffCallback : DiffUtil.ItemCallback<PokemonResult>() {
        override fun areItemsTheSame(oldItem: PokemonResult, newItem: PokemonResult): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokemonResult, newItem: PokemonResult): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            NETWORK_VIEW_TYPE
        } else {
            PRODUCT_VIEW_TYPE
        }
    }

}
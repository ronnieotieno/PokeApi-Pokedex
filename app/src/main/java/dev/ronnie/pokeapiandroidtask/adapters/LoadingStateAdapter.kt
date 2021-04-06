package dev.ronnie.pokeapiandroidtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.ronnie.pokeapiandroidtask.databinding.NetworkStateItemBinding

/**
 *created by Ronnie Otieno on 20-Dec-20.
 **/

/**
 * Loading state AAdapter used with paging 3 to show the state of data being loaded, it shows at the footer of the adapter
 */
class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        val progress = holder.binding.progressBarItem
        val txtErrorMessage = holder.binding.errorMsgItem
        val errorBtn = holder.binding.retryBtn

        progress.isVisible = loadState is LoadState.Loading
        txtErrorMessage.isVisible = loadState is LoadState.Error
        errorBtn.isVisible = loadState is LoadState.Error

        if (loadState is LoadState.Error) {
            txtErrorMessage.text = loadState.error.localizedMessage
        }
        errorBtn.setOnClickListener {
            retry.invoke()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            NetworkStateItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class LoadStateViewHolder(val binding: NetworkStateItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
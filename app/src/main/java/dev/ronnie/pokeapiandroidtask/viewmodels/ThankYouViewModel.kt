package dev.ronnie.pokeapiandroidtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ronnie.pokeapiandroidtask.data.repositories.DataStoreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 *created by Ronnie Otieno on 13-Feb-21.
 **/
@HiltViewModel
class ThankYouViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    fun saveDialogShown() = viewModelScope.launch {
        dataStoreRepository.saveDialogShown()

    }
}
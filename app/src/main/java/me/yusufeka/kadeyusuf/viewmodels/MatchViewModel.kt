package me.yusufeka.kadeyusuf.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.repositories.MatchRepository

class MatchViewModel(
    private val matchRepo: MatchRepository
) : ViewModel() {

    private var lastMatchs: MutableLiveData<List<Event>> = MutableLiveData()

    private var nextMatchs: MutableLiveData<List<Event>> = MutableLiveData()

    private var favMatchs: MutableLiveData<List<Event>> = MutableLiveData()

    private var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun isLoading(): LiveData<Boolean> = loading

    fun getLastMatch(): LiveData<List<Event>> = lastMatchs

    fun getNextMatch(): LiveData<List<Event>> = nextMatchs

    fun getFavMatch(): LiveData<List<Event>> = favMatchs

    fun updateLastMatch(id: Int) = viewModelScope.launch {
        loading.value = true
        try {
            val res = matchRepo.getLastMatch(id)
            res.events?.let { events ->
                if (events.isNotEmpty()) {
                    lastMatchs.value = events
                }
            }
        } catch (ex: Exception) {

        }
        loading.value = false
    }

    fun updateNextMatch(id: Int) = viewModelScope.launch {
        loading.value = true
        try {

            val res = matchRepo.getNextMatch(id)
            res.events?.let { events ->
                if (events.isNotEmpty()) {
                    nextMatchs.value = events
                }
            }
        } catch (ex: Exception) {

        }
        loading.value = false
    }

    fun updateFavMatch(id: Int) {

        favMatchs.value = matchRepo.getAllFavMatch(id)
    }
}
package me.yusufeka.kadeyusuf.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.repositories.TeamRepository

class MatchDetailViewModel(
    private val teamRepository: TeamRepository,
    private val matchRepository: MatchRepository
) : ViewModel() {

    private var homeTeam: MutableLiveData<Team> = MutableLiveData()

    private var awayTeam: MutableLiveData<Team> = MutableLiveData()

    private var isFavorited: MutableLiveData<Boolean> = MutableLiveData(false)

    var match: Event? = null

    fun getHomeTeam(): LiveData<Team> = homeTeam

    fun getAwayTeam(): LiveData<Team> = awayTeam

    fun isFavorited(): LiveData<Boolean> = isFavorited

    fun updateHomeTeam(id: Int) = viewModelScope.launch {
        val res = teamRepository.getTeam(id)
        res.teams?.let {
            if (it.isNotEmpty()) {
                homeTeam.value = it[0]
            }
        }
    }

    fun updateAwayTeam(id: Int) = viewModelScope.launch {
        val res = teamRepository.getTeam(id)
        res.teams?.let {
            if (it.isNotEmpty()) {
                awayTeam.value = it[0]
            }
        }
    }

    fun addToFavorit() {
        match?.let {
            matchRepository.addToFavorit(it)
            isFavorited.value = true
        }
    }

    fun removeFromFavorite() {
        match?.let {
            matchRepository.removeFromFavorite(it)
            isFavorited.value = false
        }
    }

    fun check() {
        match?.let {
            isFavorited.value = matchRepository.check(it)
        }
    }
}
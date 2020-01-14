package me.yusufeka.kadeyusuf.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.repositories.TeamRepository

class TeamDetailViewModel(
    private val teamRepository: TeamRepository
) : ViewModel() {

    var team: Team? = null

    private var isFavorited: MutableLiveData<Boolean> = MutableLiveData(false)

    fun isFavorited(): LiveData<Boolean> = isFavorited

    fun addToFavorit() {
        team?.let {
            teamRepository.addToFavorit(it)
            isFavorited.value = true
        }
    }

    fun removeFromFavorite() {
        team?.let {
            teamRepository.removeFromFavorite(it)
            isFavorited.value = false
        }
    }

    fun check() {
        team?.let {
            isFavorited.value = teamRepository.check(it)
        }
    }
}
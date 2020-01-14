package me.yusufeka.kadeyusuf.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.repositories.TeamRepository

class TeamViewModel(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private var teams: MutableLiveData<List<Team>> = MutableLiveData()

    private var favTeams: MutableLiveData<List<Team>> = MutableLiveData()

    private var errorMessage: MutableLiveData<String> = MutableLiveData("")

    fun isLoading(): LiveData<Boolean> = loading

    fun getTeams(): LiveData<List<Team>> = teams

    fun getFavTeams(): LiveData<List<Team>> = favTeams

    fun updateTeams(id: Int) = viewModelScope.launch {
        loading.value = true
        try {
            val response = teamRepository.getAllTeam(id)
            teams.value = response.teams
        } catch (ex: Exception) {
            errorMessage.value = ex.message
        }
        loading.value = false
    }

    fun updateFavTeam(id: Int) {
        favTeams.value = teamRepository.getAllFav(id)
    }
}
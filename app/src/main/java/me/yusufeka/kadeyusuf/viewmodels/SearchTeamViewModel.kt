package me.yusufeka.kadeyusuf.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import java.lang.Exception

class SearchTeamViewModel(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private var teams: MutableLiveData<List<Team>> = MutableLiveData()

    private var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private var infoMessage: MutableLiveData<String> = MutableLiveData("")

    fun isLoading(): LiveData<Boolean> = loading

    fun getTeams(): LiveData<List<Team>> = teams

    fun getInfoMessage(): LiveData<String> = infoMessage

    fun searchTeam(keyword: String, leagueId: Int) = viewModelScope.launch {
        loading.value = true
        try {
            val res = teamRepository.searchTeam(keyword)
            res.teams?.let {
                if (it.isNotEmpty()) {
                    teams.value = it.filter { team ->
                        team.idLeague.toInt() == leagueId && team.strSport == "Soccer"
                    }
                    if (teams.value!!.isEmpty()) {
                        infoMessage.value = "Data tidak ditemukan"
                    }
                }
            }
            if (res.teams == null) {
                infoMessage.value = "Data tidak ditemukan"
            }
        } catch (ex: Exception) {
            infoMessage.value = ex.message
        }
        loading.value = false
    }
}
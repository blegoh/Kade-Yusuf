package me.yusufeka.kadeyusuf.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yusufeka.kadeyusuf.models.LeagueX
import me.yusufeka.kadeyusuf.repositories.LeagueRepository
import java.lang.Exception

class LeagueViewModel(
    private val leagueRepository: LeagueRepository
) : ViewModel() {

    private var league: MutableLiveData<LeagueX> = MutableLiveData()

    private var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun isLoading(): LiveData<Boolean> = loading

    fun getLeague(): LiveData<LeagueX> = league

    fun updateLeague(id: Int) = viewModelScope.launch {
        loading.value = true
        try {
            val res = leagueRepository.getLeague(id)
            res.leagues?.let {
                if (it.isNotEmpty()) {
                    league.value = it[0]
                }
            }
        }catch (ex : Exception){

        }
        loading.value = false
    }

}
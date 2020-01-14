package me.yusufeka.kadeyusuf.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yusufeka.kadeyusuf.repositories.LeagueRepository
import me.yusufeka.kadeyusuf.viewmodels.LeagueViewModel

class LeagueFactory(
    private val leagueRepo : LeagueRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LeagueViewModel::class.java))
            return LeagueViewModel(leagueRepo) as T
        throw IllegalArgumentException()
    }
}
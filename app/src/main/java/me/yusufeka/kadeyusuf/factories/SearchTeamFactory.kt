package me.yusufeka.kadeyusuf.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.viewmodels.SearchTeamViewModel

class SearchTeamFactory(
    private val teamRepository: TeamRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchTeamViewModel::class.java))
            return SearchTeamViewModel(teamRepository) as T
        throw IllegalArgumentException()
    }
}
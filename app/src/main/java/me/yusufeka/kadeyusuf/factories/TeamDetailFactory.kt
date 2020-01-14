package me.yusufeka.kadeyusuf.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.viewmodels.TeamDetailViewModel

class TeamDetailFactory(
    private val teamRepository: TeamRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamDetailViewModel::class.java))
            return TeamDetailViewModel(teamRepository) as T
        throw IllegalArgumentException()
    }
}
package me.yusufeka.kadeyusuf.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.repositories.TeamRepository
import me.yusufeka.kadeyusuf.viewmodels.MatchDetailViewModel

class MatchDetailFactory(
    private val teamRepository: TeamRepository,
    private val matchRepository: MatchRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchDetailViewModel::class.java))
            return MatchDetailViewModel(teamRepository,matchRepository) as T
        throw IllegalArgumentException()
    }
}
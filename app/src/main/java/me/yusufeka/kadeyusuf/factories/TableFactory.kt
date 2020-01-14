package me.yusufeka.kadeyusuf.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yusufeka.kadeyusuf.repositories.LeagueRepository
import me.yusufeka.kadeyusuf.viewmodels.TableViewModel

class TableFactory(
    private val leagueRepository: LeagueRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TableViewModel::class.java))
            return TableViewModel(leagueRepository) as T
        throw IllegalArgumentException()
    }
}
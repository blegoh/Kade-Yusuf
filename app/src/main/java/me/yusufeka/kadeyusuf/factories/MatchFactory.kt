package me.yusufeka.kadeyusuf.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.viewmodels.MatchViewModel

class MatchFactory(
    private val matchRepo : MatchRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchViewModel::class.java))
            return MatchViewModel(matchRepo) as T
        throw IllegalArgumentException()
    }
}
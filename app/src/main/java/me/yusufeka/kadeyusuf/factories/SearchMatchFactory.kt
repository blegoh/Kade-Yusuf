package me.yusufeka.kadeyusuf.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.viewmodels.SearchMatchViewModel

class SearchMatchFactory(
    private val matchRepository: MatchRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchMatchViewModel::class.java))
            return SearchMatchViewModel(matchRepository) as T
        throw IllegalArgumentException()
    }
}
package me.yusufeka.kadeyusuf.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yusufeka.kadeyusuf.BuildConfig
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.networks.ApiClient
import me.yusufeka.kadeyusuf.repositories.MatchRepository
import me.yusufeka.kadeyusuf.responses.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchMatchViewModel(
    private val matchRepo : MatchRepository
) : ViewModel() {

    private var matches: MutableLiveData<List<Event>> = MutableLiveData()

    private var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private var infoMessage: MutableLiveData<String> = MutableLiveData("")

    fun isLoading(): LiveData<Boolean> = loading

    fun getMatch(): LiveData<List<Event>> = matches

    fun getInfoMessage(): LiveData<String> = infoMessage

    fun searchMatch(keyword: String, leagueId: Int) = viewModelScope.launch {
        loading.value = true
        try {
            val res = matchRepo.searchMatch(keyword)
            res.event?.let { events ->
                if (events.isNotEmpty()) {
                    matches.value = res.event.filter { event ->
                        event.idLeague?.toInt() == leagueId && event.strSport == "Soccer"
                    }
                    if (matches.value!!.isEmpty()) {
                        infoMessage.value = "Data tidak ditemukan"
                    }
                }
            }
            if (res.event == null) {
                infoMessage.value = "Data tidak ditemukan"
            }
        }catch (ex : Exception){
            infoMessage.value = ex.message
        }
        loading.value = false
    }
}
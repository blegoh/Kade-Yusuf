package me.yusufeka.kadeyusuf.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yusufeka.kadeyusuf.models.Table
import me.yusufeka.kadeyusuf.repositories.LeagueRepository

class TableViewModel(
    private val leagueRepository: LeagueRepository
) : ViewModel() {

    private var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private var table: MutableLiveData<List<Table>> = MutableLiveData()

    private var errorMessage: MutableLiveData<String> = MutableLiveData("")

    fun isLoading(): LiveData<Boolean> = loading

    fun getTable(): LiveData<List<Table>> = table

    fun updateTable(id : Int)  = viewModelScope.launch {
        loading.value = true
        try {
            val response = leagueRepository.getTable(id)
            table.value = response.table
        }catch (ex : Exception){
            Log.e("asdasd",ex.message ?: "")
            errorMessage.value = ex.message
        }
        loading.value = false
    }
}
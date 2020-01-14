package me.yusufeka.kadeyusuf.repositories

import me.yusufeka.kadeyusuf.BuildConfig
import me.yusufeka.kadeyusuf.networks.ApiClient

class LeagueRepository {

    private val service = ApiClient().getApiService()

    suspend fun getLeague(id: Int) = service.leagueDetailsById(BuildConfig.TSDB_API_KEY, id)

    suspend fun getTable(id : Int) = service.leagueTable(BuildConfig.TSDB_API_KEY, id)
}
package me.yusufeka.kadeyusuf.networks

import me.yusufeka.kadeyusuf.responses.LeagueResponse
import me.yusufeka.kadeyusuf.responses.TableResponse
import me.yusufeka.kadeyusuf.responses.MatchResponse
import me.yusufeka.kadeyusuf.responses.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("api/v1/json/{apikey}/lookupleague.php")
    suspend fun leagueDetailsById(
        @Path("apikey") apikey: String,
        @Query("id") id: Int
    ): LeagueResponse

    @GET("api/v1/json/{apikey}/eventspastleague.php")
    suspend fun lastEventByLeagueId(
        @Path("apikey") apikey: String,
        @Query("id") id: Int
    ): MatchResponse

    @GET("api/v1/json/{apikey}/eventsnextleague.php")
    suspend fun nextEventByLeagueId(
        @Path("apikey") apikey: String,
        @Query("id") id: Int
    ): MatchResponse

    @GET("api/v1/json/{apikey}/searchevents.php")
    suspend fun searchEvent(
        @Path("apikey") apikey: String,
        @Query("e") keyword: String
    ): MatchResponse

    @GET("api/v1/json/{apikey}/lookupteam.php")
    suspend fun teamDetailsById(
        @Path("apikey") apikey: String,
        @Query("id") id: Int
    ): TeamResponse

    @GET("api/v1/json/{apikey}/lookuptable.php")
    suspend fun leagueTable(
        @Path("apikey") apikey: String,
        @Query("l") id: Int
    ) : TableResponse

    @GET("api/v1/json/{apikey}/lookup_all_teams.php")
    suspend fun getAllTeams(
        @Path("apikey") apikey: String,
        @Query("id") id: Int
    ) : TeamResponse

    @GET("api/v1/json/{apikey}/searchteams.php")
    suspend fun searchTeam(
        @Path("apikey") apikey: String,
        @Query("t") keyword: String
    ): TeamResponse
}
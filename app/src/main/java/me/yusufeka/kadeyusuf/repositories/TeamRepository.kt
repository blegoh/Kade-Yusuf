package me.yusufeka.kadeyusuf.repositories

import android.util.Log
import kotlinx.coroutines.delay
import me.yusufeka.kadeyusuf.BuildConfig
import me.yusufeka.kadeyusuf.utils.MyDatabaseOpenHelper
import me.yusufeka.kadeyusuf.models.Team
import me.yusufeka.kadeyusuf.networks.ApiClient
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamRepository {

    var database: MyDatabaseOpenHelper? = null

    private val service = ApiClient().getApiService()

    suspend fun getTeam(id: Int) =
        service.teamDetailsById(BuildConfig.TSDB_API_KEY, id)

    suspend fun getAllTeam(id: Int) =
        service.getAllTeams(BuildConfig.TSDB_API_KEY, id)

    suspend fun searchTeam(keywoard : String) =
        service.searchTeam(BuildConfig.TSDB_API_KEY, keywoard)

    fun addToFavorit(team: Team) {
        database?.use {
            insert(
                Team.TABLE_TEAM_FAVORITE,
                Team.ALTERNATE to team.strAlternate,
                Team.COUNTRY to team.strCountry,
                Team.DESCRIPTION_CN to team.strDescriptionCN,
                Team.DESCRIPTION_DE to team.strDescriptionDE,
                Team.DESCRIPTION_EN to team.strDescriptionEN,
                Team.DESCRIPTION_ES to team.strDescriptionES,
                Team.DESCRIPTION_FR to team.strDescriptionFR,
                Team.DESCRIPTION_HU to team.strDescriptionHU,
                Team.DESCRIPTION_IL to team.strDescriptionIL,
                Team.DESCRIPTION_IT to team.strDescriptionIT,
                Team.DESCRIPTION_JP to team.strDescriptionJP,
                Team.DESCRIPTION_NL to team.strDescriptionNL,
                Team.DESCRIPTION_NO to team.strDescriptionNO,
                Team.DESCRIPTION_PL to team.strDescriptionPL,
                Team.DESCRIPTION_PT to team.strDescriptionPT,
                Team.DESCRIPTION_RU to team.strDescriptionRU,
                Team.DESCRIPTION_SE to team.strDescriptionSE,
                Team.DIVISION to team.strDivision,
                Team.FACEBOOK to team.strFacebook,
                Team.FORMED_YEAR to team.intFormedYear,
                Team.GENDER to team.strGender,
                Team.INSTAGRAM to team.strInstagram,
                Team.KEYWORDS to team.strKeywords,
                Team.LEAGUE to team.strLeague,
                Team.LEAGUE_ID to team.idLeague,
                Team.LOCKED to team.strLocked,
                Team.LOVED to team.intLoved,
                Team.MANAGER to team.strManager,
                Team.RSS to team.strRSS,
                Team.SOCCER_XML_ID to team.idSoccerXML,
                Team.SPORT to team.strSport,
                Team.STADIUM to team.strStadium,
                Team.STADIUM_CAPACITY to team.intStadiumCapacity,
                Team.STADIUM_DESCRIPTION to team.strStadiumDescription,
                Team.STADIUM_LOCATION to team.strStadiumLocation,
                Team.STADIUM_THUMB to team.strStadiumThumb,
                Team.TEAM to team.strTeam,
                Team.TEAM_BADGE to team.strTeamBadge,
                Team.TEAM_BANNER to team.strTeamBanner,
                Team.TEAM_FANART1 to team.strTeamFanart1,
                Team.TEAM_FANART2 to team.strTeamFanart2,
                Team.TEAM_FANART3 to team.strTeamFanart3,
                Team.TEAM_FANART4 to team.strTeamFanart4,
                Team.TEAM_ID to team.idTeam,
                Team.TEAM_JERSEY to team.strTeamJersey,
                Team.TEAM_LOGO to team.strTeamLogo,
                Team.TEAM_SHORT to team.strTeamShort,
                Team.TWITTER to team.strTwitter,
                Team.WEBSITE to team.strWebsite,
                Team.YOUTUBE to team.strYoutube
            )
        }
    }

    fun removeFromFavorite(team: Team) {
        database?.use {
            delete(
                Team.TABLE_TEAM_FAVORITE, "(TEAM_ID = {id})",
                "id" to team.idTeam
            )

        }
    }

    fun check(team: Team): Boolean {
        var r = false
        database?.use {
            val result = select(Team.TABLE_TEAM_FAVORITE)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to team.idTeam
                )
            val favorite = result.parseList(classParser<Team>())
            r = favorite.isNotEmpty()
        }
        return r
    }

    fun getAllFav(id: Int): List<Team> {
        var teams: List<Team> = listOf()
        database?.let { db ->
            db.use {
                val result = select(Team.TABLE_TEAM_FAVORITE)

                val x = result.parseList(classParser<Team>()).filter {
                    it.idLeague.toInt() == id
                }
                teams = x
            }
        }
        return teams
    }
}
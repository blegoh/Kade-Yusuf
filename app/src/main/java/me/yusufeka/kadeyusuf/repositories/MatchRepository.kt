package me.yusufeka.kadeyusuf.repositories

import me.yusufeka.kadeyusuf.BuildConfig
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.networks.ApiClient
import me.yusufeka.kadeyusuf.utils.MyDatabaseOpenHelper
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchRepository {

    private val service = ApiClient().getApiService()

    private val apiKey = BuildConfig.TSDB_API_KEY

    var database: MyDatabaseOpenHelper? = null

    suspend fun getLastMatch(id: Int) = service.lastEventByLeagueId(apiKey, id)

    suspend fun getNextMatch(id: Int) = service.nextEventByLeagueId(apiKey, id)

    suspend fun searchMatch(keyword: String) = service.searchEvent(apiKey, keyword)

    fun addToFavorit(match: Event) {
        database?.use {
            insert(
                Event.TABLE_MATCH_FAVORITE,
                Event.DATE_EVENT to match.dateEvent,
                Event.DATE_EVENT_LOCAL to match.dateEventLocal,
                Event.AWAY_TEAM_ID to match.idAwayTeam,
                Event.EVENT_ID to match.idEvent,
                Event.HOME_TEAM_ID to match.idHomeTeam,
                Event.LEAGUE_ID to match.idLeague,
                Event.SOCCER_XML_ID to match.idSoccerXML,
                Event.AWAY_SCORE to match.intAwayScore,
                Event.AWAY_SHOTS to match.intAwayShots,
                Event.HOME_SCORE to match.intHomeScore,
                Event.HOME_SHOTS to match.intHomeShots,
                Event.ROUND to match.intRound,
                Event.SPECTATORS to match.intSpectators,
                Event.AWAY_FORMATION to match.strAwayFormation,
                Event.AWAY_GOAL_DETAILS to match.strAwayGoalDetails,
                Event.AWAY_LINEUP_DEFENSE to match.strAwayLineupDefense,
                Event.AWAY_LINEUP_FORWARD to match.strAwayLineupForward,
                Event.AWAY_LINEUP_GOALKEEPER to match.strAwayLineupGoalkeeper,
                Event.AWAY_LINEUP_MIDFIELD to match.strAwayLineupMidfield,
                Event.AWAY_LINEUP_SUBSTITUTES to match.strAwayLineupSubstitutes,
                Event.AWAY_RED_CARDS to match.strAwayRedCards,
                Event.AWAY_TEAM to match.strAwayTeam,
                Event.AWAY_YELLOW_CARDS to match.strAwayYellowCards,
                Event.BANNER to match.strBanner,
                Event.CIRCUIT to match.strCircuit,
                Event.CITY to match.strCity,
                Event.COUNTRY to match.strCountry,
                Event.DATE to match.strDate,
                Event.DESCRIPTION_EN to match.strDescriptionEN,
                Event.EVENT to match.strEvent,
                Event.EVENT_ALTERNATE to match.strEventAlternate,
                Event.FANART to match.strFanart,
                Event.FILENAME to match.strFilename,
                Event.HOME_FORMATION to match.strHomeFormation,
                Event.HOME_GOAL_DETAILS to match.strHomeGoalDetails,
                Event.HOME_LINEUP_DEFENSE to match.strHomeLineupDefense,
                Event.HOME_LINEUP_FORWARD to match.strHomeLineupForward,
                Event.HOME_LINEUP_GOALKEEPER to match.strHomeLineupGoalkeeper,
                Event.HOME_LINEUP_MIDFIELD to match.strHomeLineupMidfield,
                Event.HOME_LINEUP_SUBSTITUTES to match.strHomeLineupSubstitutes,
                Event.HOME_RED_CARDS to match.strHomeRedCards,
                Event.HOME_TEAM to match.strHomeTeam,
                Event.HOME_YELLOW_CARDS to match.strHomeYellowCards,
                Event.LEAGUE to match.strLeague,
                Event.LOCKED to match.strLocked,
                Event.MAP to match.strMap,
                Event.POSTER to match.strPoster,
                Event.RESULT to match.strResult,
                Event.SEASON to match.strSeason,
                Event.SPORT to match.strSport,
                Event.TVSTATION to match.strTVStation,
                Event.THUMB to match.strThumb,
                Event.TIME to match.strTime,
                Event.TIME_LOCAL to match.strTimeLocal,
                Event.TWEET1 to match.strTweet1,
                Event.TWEET2 to match.strTweet2,
                Event.TWEET3 to match.strTweet3,
                Event.VIDEO to match.strVideo
            )
        }
    }

    fun removeFromFavorite(match: Event) {
        database?.use {
            delete(
                Event.TABLE_MATCH_FAVORITE, "(EVENT_ID = {id})",
                "id" to match.idEvent!!
            )

        }
    }

    fun check(match: Event): Boolean {
        var r = false
        database?.use {
            val result = select(Event.TABLE_MATCH_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to match.idEvent!!
                )
            val favorite = result.parseList(classParser<Event>())
            r = favorite.isNotEmpty()
        }
        return r
    }

    fun getAllFavMatch(id: Int): List<Event> {
        var list: List<Event> = ArrayList()
        database?.let { db ->
            db.use {
                val result = select(Event.TABLE_MATCH_FAVORITE)
                val x = result.parseList(classParser<Event>()).filter {
                    it.idLeague?.toInt() == id
                }
                list = x
            }
        }
        return  list
    }
}
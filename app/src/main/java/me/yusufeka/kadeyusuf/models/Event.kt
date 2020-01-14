package me.yusufeka.kadeyusuf.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val id: Long?,
    val dateEvent: String?,
    val dateEventLocal: String?,
    val idAwayTeam: String,
    val idEvent: String?,
    val idHomeTeam: String,
    val idLeague: String?,
    val idSoccerXML: String?,
    val intAwayScore: String?,
    val intAwayShots: String?,
    val intHomeScore: String?,
    val intHomeShots: String?,
    val intRound: String?,
    val intSpectators: String?,
    val strAwayFormation: String?,
    val strAwayGoalDetails: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupSubstitutes: String?,
    val strAwayRedCards: String?,
    val strAwayTeam: String?,
    val strAwayYellowCards: String?,
    val strBanner: String?,
    val strCircuit: String?,
    val strCity: String?,
    val strCountry: String?,
    val strDate: String?,
    val strDescriptionEN: String?,
    val strEvent: String?,
    val strEventAlternate: String?,
    val strFanart: String?,
    val strFilename: String?,
    val strHomeFormation: String?,
    val strHomeGoalDetails: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupSubstitutes: String?,
    val strHomeRedCards: String?,
    val strHomeTeam: String?,
    val strHomeYellowCards: String?,
    val strLeague: String?,
    val strLocked: String?,
    val strMap: String?,
    val strPoster: String?,
    val strResult: String?,
    val strSeason: String?,
    val strSport: String?,
    val strTVStation: String?,
    val strThumb: String?,
    val strTime: String?,
    val strTimeLocal: String?,
    val strTweet1: String?,
    val strTweet2: String?,
    val strTweet3: String?,
    val strVideo: String?
) : Parcelable {

    companion object {
        const val TABLE_MATCH_FAVORITE: String = "TABLE_MATCH_FAVORITE"
        const val ID: String = "ID_"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val DATE_EVENT_LOCAL: String = "DATE_EVENT_LOCAL"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val LEAGUE_ID: String = "LEAGUE_ID"
        const val SOCCER_XML_ID: String = "SOCCER_XML_ID"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val ROUND: String = "ROUND"
        const val SPECTATORS: String = "SPECTATORS"
        const val AWAY_FORMATION: String = "AWAY_FORMATION"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val AWAY_LINEUP_DEFENSE: String = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_FORWARD: String = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_GOALKEEPER: String = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_MIDFIELD: String = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_SUBSTITUTES: String = "AWAY_LINEUP_SUBSTITUTES"
        const val AWAY_RED_CARDS: String = "AWAY_RED_CARDS"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_YELLOW_CARDS: String = "AWAY_YELLOW_CARDS"
        const val BANNER: String = "BANNER"
        const val CIRCUIT: String = "CIRCUIT"
        const val CITY: String = "CITY"
        const val COUNTRY: String = "COUNTRY"
        const val DATE: String = "DATE"
        const val DESCRIPTION_EN: String = "DESCRIPTION_EN"
        const val EVENT: String = "EVENT"
        const val EVENT_ALTERNATE: String = "EVENT_ALTERNATE"
        const val FANART: String = "FANART"
        const val FILENAME: String = "FILENAME"
        const val HOME_FORMATION: String = "HOME_FORMATION"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val HOME_LINEUP_DEFENSE: String = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_FORWARD: String = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_GOALKEEPER: String = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_MIDFIELD: String = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_SUBSTITUTES: String = "HOME_LINEUP_SUBSTITUTES"
        const val HOME_RED_CARDS: String = "HOME_RED_CARDS"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_YELLOW_CARDS: String = "HOME_YELLOW_CARDS"
        const val LEAGUE: String = "LEAGUE"
        const val LOCKED: String = "LOCKED"
        const val MAP: String = "MAP"
        const val POSTER: String = "POSTER"
        const val RESULT: String = "RESULT"
        const val SEASON: String = "SEASON"
        const val SPORT: String = "SPORT"
        const val TVSTATION: String = "TVSTATION"
        const val THUMB: String = "THUMB"
        const val TIME: String = "TIME"
        const val TIME_LOCAL: String = "TIME_LOCAL"
        const val TWEET1: String = "TWEET1"
        const val TWEET2: String = "TWEET2"
        const val TWEET3: String = "TWEET3"
        const val VIDEO: String = "VIDEO"
    }
}
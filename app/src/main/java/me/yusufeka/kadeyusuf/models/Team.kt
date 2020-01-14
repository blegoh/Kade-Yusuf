package me.yusufeka.kadeyusuf.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: Long?,
    val idLeague: String,
    val idSoccerXML: String?,
    val idTeam: String,
    val intFormedYear: String?,
    val intLoved: String?,
    val intStadiumCapacity: String?,
    val strAlternate: String?,
    val strCountry: String?,
    val strDescriptionCN: String?,
    val strDescriptionDE: String?,
    val strDescriptionEN: String?,
    val strDescriptionES: String?,
    val strDescriptionFR: String?,
    val strDescriptionHU: String?,
    val strDescriptionIL: String?,
    val strDescriptionIT: String?,
    val strDescriptionJP: String?,
    val strDescriptionNL: String?,
    val strDescriptionNO: String?,
    val strDescriptionPL: String?,
    val strDescriptionPT: String?,
    val strDescriptionRU: String?,
    val strDescriptionSE: String?,
    val strDivision: String?,
    val strFacebook: String?,
    val strGender: String?,
    val strInstagram: String?,
    val strKeywords: String?,
    val strLeague: String?,
    val strLocked: String?,
    val strManager: String?,
    val strRSS: String?,
    val strSport: String?,
    val strStadium: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val strStadiumThumb: String?,
    val strTeam: String,
    val strTeamBadge: String?,
    val strTeamBanner: String?,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
    val strTeamShort: String?,
    val strTwitter: String?,
    val strWebsite: String?,
    val strYoutube: String?
) : Parcelable {
    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val LEAGUE_ID: String = "LEAGUE_ID"
        const val SOCCER_XML_ID: String = "SOCCER_XML_ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val LOVED: String = "LOVED"
        const val STADIUM_CAPACITY: String = "STADIUM_CAPACITY"
        const val ALTERNATE: String = "ALTERNATE"
        const val COUNTRY: String = "COUNTRY"
        const val DESCRIPTION_CN: String = "DESCRIPTION_CN"
        const val DESCRIPTION_DE: String = "DESCRIPTION_DE"
        const val DESCRIPTION_EN: String = "DESCRIPTION_EN"
        const val DESCRIPTION_ES: String = "DESCRIPTION_ES"
        const val DESCRIPTION_FR: String = "DESCRIPTION_FR"
        const val DESCRIPTION_HU: String = "DESCRIPTION_HU"
        const val DESCRIPTION_IL: String = "DESCRIPTION_IL"
        const val DESCRIPTION_IT: String = "DESCRIPTION_IT"
        const val DESCRIPTION_JP: String = "DESCRIPTION_JP"
        const val DESCRIPTION_NL: String = "DESCRIPTION_NL"
        const val DESCRIPTION_NO: String = "DESCRIPTION_NO"
        const val DESCRIPTION_PL: String = "DESCRIPTION_PL"
        const val DESCRIPTION_PT: String = "DESCRIPTION_PT"
        const val DESCRIPTION_RU: String = "DESCRIPTION_RU"
        const val DESCRIPTION_SE: String = "DESCRIPTION_SE"
        const val DIVISION: String = "DIVISION"
        const val FACEBOOK: String = "FACEBOOK"
        const val GENDER: String = "GENDER"
        const val INSTAGRAM: String = "INSTAGRAM"
        const val KEYWORDS: String = "KEYWORDS"
        const val LEAGUE: String = "LEAGUE"
        const val LOCKED: String = "LOCKED"
        const val MANAGER: String = "MANAGER"
        const val RSS: String = "RSS"
        const val SPORT: String = "SPORT"
        const val STADIUM: String = "STADIUM"
        const val STADIUM_DESCRIPTION: String = "STADIUM_DESCRIPTION"
        const val STADIUM_LOCATION: String = "STADIUM_LOCATION"
        const val STADIUM_THUMB: String = "STADIUM_THUMB"
        const val TEAM: String = "TEAM"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_BANNER: String = "TEAM_BANNER"
        const val TEAM_FANART1: String = "TEAM_FANART1"
        const val TEAM_FANART2: String = "TEAM_FANART2"
        const val TEAM_FANART3: String = "TEAM_FANART3"
        const val TEAM_FANART4: String = "TEAM_FANART4"
        const val TEAM_JERSEY: String = "TEAM_JERSEY"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAM_SHORT: String = "TEAM_SHORT"
        const val TWITTER: String = "TWITTER"
        const val WEBSITE: String = "WEBSITE"
        const val YOUTUBE: String = "YOUTUBE"
    }
}
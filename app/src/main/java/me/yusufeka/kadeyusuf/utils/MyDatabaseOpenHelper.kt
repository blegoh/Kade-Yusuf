package me.yusufeka.kadeyusuf.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import me.yusufeka.kadeyusuf.models.Event
import me.yusufeka.kadeyusuf.models.Team
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance =
                    MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createMatchFavTable(db)
        createTeamFavTable(db)
    }

    private fun createMatchFavTable(db: SQLiteDatabase) {
        db.createTable(
            Event.TABLE_MATCH_FAVORITE, true,
            Event.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Event.DATE_EVENT to TEXT,
            Event.DATE_EVENT_LOCAL to TEXT,
            Event.AWAY_TEAM_ID to TEXT,
            Event.EVENT_ID to TEXT + UNIQUE,
            Event.HOME_TEAM_ID to TEXT,
            Event.LEAGUE_ID to TEXT,
            Event.SOCCER_XML_ID to TEXT,
            Event.AWAY_SCORE to TEXT,
            Event.AWAY_SHOTS to TEXT,
            Event.HOME_SCORE to TEXT,
            Event.HOME_SHOTS to TEXT,
            Event.ROUND to TEXT,
            Event.SPECTATORS to TEXT,
            Event.AWAY_FORMATION to TEXT,
            Event.AWAY_GOAL_DETAILS to TEXT,
            Event.AWAY_LINEUP_DEFENSE to TEXT,
            Event.AWAY_LINEUP_FORWARD to TEXT,
            Event.AWAY_LINEUP_GOALKEEPER to TEXT,
            Event.AWAY_LINEUP_MIDFIELD to TEXT,
            Event.AWAY_LINEUP_SUBSTITUTES to TEXT,
            Event.AWAY_RED_CARDS to TEXT,
            Event.AWAY_TEAM to TEXT,
            Event.AWAY_YELLOW_CARDS to TEXT,
            Event.BANNER to TEXT,
            Event.CIRCUIT to TEXT,
            Event.CITY to TEXT,
            Event.COUNTRY to TEXT,
            Event.DATE to TEXT,
            Event.DESCRIPTION_EN to TEXT,
            Event.EVENT to TEXT,
            Event.EVENT_ALTERNATE to TEXT,
            Event.FANART to TEXT,
            Event.FILENAME to TEXT,
            Event.HOME_FORMATION to TEXT,
            Event.HOME_GOAL_DETAILS to TEXT,
            Event.HOME_LINEUP_DEFENSE to TEXT,
            Event.HOME_LINEUP_FORWARD to TEXT,
            Event.HOME_LINEUP_GOALKEEPER to TEXT,
            Event.HOME_LINEUP_MIDFIELD to TEXT,
            Event.HOME_LINEUP_SUBSTITUTES to TEXT,
            Event.HOME_RED_CARDS to TEXT,
            Event.HOME_TEAM to TEXT,
            Event.HOME_YELLOW_CARDS to TEXT,
            Event.LEAGUE to TEXT,
            Event.LOCKED to TEXT,
            Event.MAP to TEXT,
            Event.POSTER to TEXT,
            Event.RESULT to TEXT,
            Event.SEASON to TEXT,
            Event.SPORT to TEXT,
            Event.TVSTATION to TEXT,
            Event.THUMB to TEXT,
            Event.TIME to TEXT,
            Event.TIME_LOCAL to TEXT,
            Event.TWEET1 to TEXT,
            Event.TWEET2 to TEXT,
            Event.TWEET3 to TEXT,
            Event.VIDEO to TEXT
        )
    }

    private fun createTeamFavTable(db: SQLiteDatabase) {
        db.createTable(
            Team.TABLE_TEAM_FAVORITE, true,
            Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Team.LEAGUE_ID to TEXT,
            Team.SOCCER_XML_ID to TEXT,
            Team.TEAM_ID to TEXT,
            Team.FORMED_YEAR to TEXT,
            Team.LOVED to TEXT,
            Team.STADIUM_CAPACITY to TEXT,
            Team.ALTERNATE to TEXT,
            Team.COUNTRY to TEXT,
            Team.DESCRIPTION_CN to TEXT,
            Team.DESCRIPTION_DE to TEXT,
            Team.DESCRIPTION_EN to TEXT,
            Team.DESCRIPTION_ES to TEXT,
            Team.DESCRIPTION_FR to TEXT,
            Team.DESCRIPTION_HU to TEXT,
            Team.DESCRIPTION_IL to TEXT,
            Team.DESCRIPTION_IT to TEXT,
            Team.DESCRIPTION_JP to TEXT,
            Team.DESCRIPTION_NL to TEXT,
            Team.DESCRIPTION_NO to TEXT,
            Team.DESCRIPTION_PL to TEXT,
            Team.DESCRIPTION_PT to TEXT,
            Team.DESCRIPTION_RU to TEXT,
            Team.DESCRIPTION_SE to TEXT,
            Team.DIVISION to TEXT,
            Team.FACEBOOK to TEXT,
            Team.GENDER to TEXT,
            Team.INSTAGRAM to TEXT,
            Team.KEYWORDS to TEXT,
            Team.LEAGUE to TEXT,
            Team.LOCKED to TEXT,
            Team.MANAGER to TEXT,
            Team.RSS to TEXT,
            Team.SPORT to TEXT,
            Team.STADIUM to TEXT,
            Team.STADIUM_DESCRIPTION to TEXT,
            Team.STADIUM_LOCATION to TEXT,
            Team.STADIUM_THUMB to TEXT,
            Team.TEAM to TEXT,
            Team.TEAM_BADGE to TEXT,
            Team.TEAM_BANNER to TEXT,
            Team.TEAM_FANART1 to TEXT,
            Team.TEAM_FANART2 to TEXT,
            Team.TEAM_FANART3 to TEXT,
            Team.TEAM_FANART4 to TEXT,
            Team.TEAM_JERSEY to TEXT,
            Team.TEAM_LOGO to TEXT,
            Team.TEAM_SHORT to TEXT,
            Team.TWITTER to TEXT,
            Team.WEBSITE to TEXT,
            Team.YOUTUBE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Event.TABLE_MATCH_FAVORITE, true)
        db.dropTable(Team.TABLE_TEAM_FAVORITE, true)
    }

    val Context.database: MyDatabaseOpenHelper
        get() = getInstance(
            applicationContext
        )
}
package com.yeputra.footballclub.repository.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.yeputra.footballclub.model.DB_FAVORITE
import com.yeputra.footballclub.model.MatchFavorite
import com.yeputra.footballclub.model.TeamFavorite
import org.jetbrains.anko.db.*

class FavoriteDatabase(
    context: Context
): ManagedSQLiteOpenHelper (
    context, DB_FAVORITE, null, 2
) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: FavoriteDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteDatabase {
            if (instance == null) {
                instance = FavoriteDatabase(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MatchFavorite.MATCH_FAVORITE, true,
            MatchFavorite.EVENT_ID to TEXT + PRIMARY_KEY,
            MatchFavorite.EVENT_DATE to TEXT,
            MatchFavorite.HOME_NAME to TEXT,
            MatchFavorite.HOME_SCORE to TEXT,
            MatchFavorite.AWAY_NAME to TEXT,
            MatchFavorite.AWAY_SCORE to TEXT)

        db.createTable(TeamFavorite.TEAM_FAVORITE, true,
            TeamFavorite.TEAM_ID to TEXT + PRIMARY_KEY,
            TeamFavorite.TEAM_NAME to TEXT,
            TeamFavorite.TEAM_LOGO to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchFavorite.MATCH_FAVORITE, true)
        db.dropTable(TeamFavorite.TEAM_FAVORITE, true)
    }
}


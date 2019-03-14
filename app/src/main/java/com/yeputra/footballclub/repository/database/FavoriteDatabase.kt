package com.yeputra.footballclub.repository.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.yeputra.footballclub.model.Favorite
import org.jetbrains.anko.db.*

class FavoriteDatabase(
    private val context: Context
): ManagedSQLiteOpenHelper (
    context,Favorite.TABLE_FAVORITE, null, 1
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
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.EVENT_ID to TEXT + PRIMARY_KEY,
            Favorite.EVENT_DATE to TEXT,
            Favorite.HOME_NAME to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_NAME to TEXT,
            Favorite.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}


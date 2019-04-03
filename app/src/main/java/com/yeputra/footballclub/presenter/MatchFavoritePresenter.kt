package com.yeputra.footballclub.presenter

import android.content.Context
import com.yeputra.footballclub.base.BasePresenter
import com.yeputra.footballclub.base.IBaseView
import com.yeputra.footballclub.model.MatchFavorite
import com.yeputra.footballclub.repository.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchFavoritePresenter(
    private val activity: IBaseView
): BasePresenter(activity) {
    private val context: Context by lazy { activity.getContextView() }

    fun add(favorite: MatchFavorite, listener: (Boolean)-> Unit) {
        context.database.use {
            val status = insert(MatchFavorite.MATCH_FAVORITE,
                MatchFavorite.EVENT_ID to favorite.eventId,
                MatchFavorite.EVENT_DATE to favorite.eventDate,
                MatchFavorite.HOME_NAME to favorite.homeName,
                MatchFavorite.HOME_SCORE to favorite.homeScore,
                MatchFavorite.AWAY_NAME to favorite.awayName,
                MatchFavorite.AWAY_SCORE to favorite.awayScore)
            listener(status > -1)
        }
    }

    fun delete(id: String, listener: (Boolean)-> Unit) {
        context.database.use {
            val status = delete(MatchFavorite.MATCH_FAVORITE,
                MatchFavorite.EVENT_ID + "={id}",
                "id" to id)
            listener(status > -1)
        }
    }

    fun findAll(listener: (MutableList<MatchFavorite>) -> Unit){
        context.database.use {
            val data = mutableListOf<MatchFavorite>()
            val result = select(MatchFavorite.MATCH_FAVORITE)
            data.addAll(result.parseList(classParser()))

            activity.hideProgressbar()
            listener(data)
        }
    }

    fun findOne(id: String): MatchFavorite? {
        var data: MatchFavorite? = null
        context.database.use {
            val result = select(MatchFavorite.MATCH_FAVORITE)
                .whereArgs(MatchFavorite.EVENT_ID + "={id}",
                    "id" to id)
            val fav = result.parseList(classParser<MatchFavorite>())
            if(!fav.isEmpty())
                data = fav[0]
        }
        return data
    }
}
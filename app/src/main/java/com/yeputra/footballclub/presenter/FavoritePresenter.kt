package com.yeputra.footballclub.presenter

import android.content.Context
import com.yeputra.footballclub.base.BasePresenter
import com.yeputra.footballclub.base.IBaseView
import com.yeputra.footballclub.model.Favorite
import com.yeputra.footballclub.repository.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FavoritePresenter(private val activity: IBaseView): BasePresenter(activity) {
    private val context: Context by lazy { activity.getContextView() }

    fun add(favorite: Favorite, listener: (Boolean)-> Unit) {
        context.database.use {
            val status = insert(Favorite.TABLE_FAVORITE,
                Favorite.EVENT_ID to favorite.eventId,
                Favorite.EVENT_DATE to favorite.eventDate,
                Favorite.HOME_NAME to favorite.homeName,
                Favorite.HOME_SCORE to favorite.homeScore,
                Favorite.AWAY_NAME to favorite.awayName,
                Favorite.AWAY_SCORE to favorite.awayScore)
            listener(status > -1)
        }
    }

    fun delete(id: String, listener: (Boolean)-> Unit) {
        context.database.use {
            val status = delete(Favorite.TABLE_FAVORITE,
                Favorite.EVENT_ID + "={id}",
                "id" to id)
            listener(status > -1)
        }
    }

    fun findAll(listener: (MutableList<Favorite>) -> Unit){
        context.database.use {
            val data = mutableListOf<Favorite>()
            val result = select(Favorite.TABLE_FAVORITE)
            data.addAll(result.parseList(classParser()))

            activity.hideProgressbar()
            listener(data)
        }
    }

    fun findOne(id: String): Favorite? {
        var data: Favorite? = null
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(Favorite.EVENT_ID + "={id}",
                    "id" to id)
            val fav = result.parseList(classParser<Favorite>())
            if(!fav.isEmpty())
                data = fav[0]
        }
        return data
    }
}
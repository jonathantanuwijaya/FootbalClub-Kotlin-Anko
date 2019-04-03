package com.yeputra.footballclub.presenter

import android.content.Context
import com.yeputra.footballclub.base.BasePresenter
import com.yeputra.footballclub.base.IBaseView
import com.yeputra.footballclub.model.TeamFavorite
import com.yeputra.footballclub.repository.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamFavoritePresenter(
    private val activity: IBaseView
): BasePresenter(activity) {
    private val context: Context by lazy { activity.getContextView() }

    fun add(favorite: TeamFavorite, listener: (Boolean)-> Unit) {
        context.database.use {
            val status = insert(TeamFavorite.TEAM_FAVORITE,
                TeamFavorite.TEAM_ID to favorite.teamId,
                TeamFavorite.TEAM_NAME to favorite.teamName,
                TeamFavorite.TEAM_LOGO to favorite.teamLogo)
            listener(status > -1)
        }
    }

    fun delete(id: String, listener: (Boolean)-> Unit) {
        context.database.use {
            val status = delete(TeamFavorite.TEAM_FAVORITE,
                TeamFavorite.TEAM_ID + "={id}",
                "id" to id)
            listener(status > -1)
        }
    }

    fun findAll(listener: (MutableList<TeamFavorite>) -> Unit){
        context.database.use {
            val data = mutableListOf<TeamFavorite>()
            val result = select(TeamFavorite.TEAM_FAVORITE)
            data.addAll(result.parseList(classParser()))

            activity.hideProgressbar()
            listener(data)
        }
    }

    fun findOne(id: String): TeamFavorite? {
        var data: TeamFavorite? = null
        context.database.use {
            val result = select(TeamFavorite.TEAM_FAVORITE)
                .whereArgs(TeamFavorite.TEAM_ID + "={id}",
                    "id" to id)
            val fav = result.parseList(classParser<TeamFavorite>())
            if(!fav.isEmpty())
                data = fav[0]
        }
        return data
    }
}
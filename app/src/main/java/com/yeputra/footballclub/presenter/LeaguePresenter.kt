package com.yeputra.footballclub.presenter

import com.yeputra.footballclub.base.BasePresenter
import com.yeputra.footballclub.base.IBaseView
import com.yeputra.footballclub.repository.api.ApiRespository
import com.yeputra.footballclub.utils.RestClient
import com.yeputra.footballclub.utils.RxUtils


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:54
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class LeaguePresenter(
    private val v: IBaseView
): BasePresenter(v) {

    private val api: ApiRespository by lazy {
        RestClient
            .get()
            .create(ApiRespository::class.java)
    }

    fun getStanding(leagueId: String){
        v.showProgressbar()
        subscriber = api.getStandings(leagueId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }

    fun getTeams(leagueId: String){
        v.showProgressbar()
        subscriber = api.getTeams(leagueId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }

    fun getTeam(teamName: String){
        subscriber = api.getTeam(teamName)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }

    fun getPlayers(teamId: String){
        subscriber = api.getPlayers(teamId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }

    fun getLastMatch(leagueId: String){
        v.showProgressbar()
        subscriber = api.getLastMatch(leagueId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }

    fun getNextMatch(leagueId: String){
        v.showProgressbar()
        subscriber = api.getNextMatch(leagueId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }

    fun getDetail(eventId: String) {
        v.showProgressbar()
        subscriber = api.getMatch(eventId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }


    fun searchEvent(eventName: String){
        subscriber = api.searchMatch(eventName)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
    }

}
package com.yeputra.footballclub.presenter

import com.yeputra.footballclub.R
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

    fun getLastMatch(leagueId: String){
        v.showProgressbar()
        subscriber = api.getLastMatch(leagueId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(
            {
                v.hideProgressbar()
                v.onPresenterSuccess(it)
            },
            {
                v.hideProgressbar()
                v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
            }
        )
    }

    fun getNextMatch(leagueId: String){
        v.showProgressbar()
        subscriber = api.getNextMatch(leagueId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(
            {
                v.hideProgressbar()
                v.onPresenterSuccess(it)
            },
            {
                v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
                v.hideProgressbar()
            }
        )
    }

    fun getDetail(eventId: String) {
        v.showProgressbar()
        subscriber = api.getDetail(eventId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(
                {
                    v.onPresenterSuccess(it)
                    v.hideProgressbar()
                },
                {
                    v.hideProgressbar()
                    v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
                }
            )
    }

    fun getTeam(teamId: String){
        subscriber = api.getTeam(teamId)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(
            {
                v.onPresenterSuccess(it.teams?.get(0))
            },
            {
                v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
            }
        )
    }

    fun searchEvent(eventName: String){
        subscriber = api.searchEvent(eventName)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(
                {
                    v.onPresenterSuccess(it)
                },
                {
                    v.onPresenterFailed(contextView.getString(R.string.no_internet_connection))
                }
            )
    }

}